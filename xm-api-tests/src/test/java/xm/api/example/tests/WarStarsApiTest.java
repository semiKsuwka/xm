package xm.api.example.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import xm.api.example.entities.Film;
import xm.api.example.entities.Person;

import java.io.InputStream;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WarStarsApiTest {


    private static String baseUrl = "https://swapi.dev/api/";
    /*
    Automation testing task #2 (API)
Resource: https://swapi.dev/
Endpoints examples:
•	find in site tutorial
Tools: Java AND (Rest Assured OR <your_choice>)

Use Case:
1.	Search for a person with the name Vader.
2.	Using previous response (1) find which film that Darth Vader joined has the less planets.
3.	Using previous responses (1) & (2) verify if Vader's starship is on film from response (2).
4.	Find the oldest person ever played in all Star Wars films with less than 10 requests.
5.	Create contract (Json schema validation) test for /people API.


     */

    private Person searchForThePerson (String name){
       List<Person> persons = RestAssured.given()
                .baseUri(baseUrl)
                .param("search",name )
                .get("people")
                .then()
                .extract()
                .jsonPath()
                .getList("results", Person.class);

        return ( persons.size() == 1 ) ? persons.get(0) : new Person();
    }


    private List<Film> getFilmPlanetsCount(List<String> filmList) {
        List <Film> films = new ArrayList<>();

        for (String film :filmList) {
            int size = RestAssured.given()
                    .get(film)
                    .then()
                    .extract()
                    .jsonPath()
                    .getList("planets")
                    .size();

            films.add(new Film (film, size));

        }

        return films;
    }


    @SneakyThrows
    public List<Person> getPersonsList(){
        List <Person> personsList = new ArrayList<>();
        String next = baseUrl + "people/";
        ObjectMapper mapper = new ObjectMapper();

        while (!"null".equals(next)) {
            String temp = RestAssured.given()
                    .get(next)
                    .then()
                    .extract()
                    .body()
                    .asString();



            JsonNode actualObj = mapper.readTree(temp);
            next = actualObj.path("next").asText();

            Person[] people = mapper.readValue(actualObj.path("results").toString(), Person[].class);
            personsList.addAll(Lists.newArrayList(people));
        }
        return personsList;
    }

    private Double convertToAge(String birthYear){
        return Double.valueOf(birthYear.replace("BBY", "").replace(
                "ABY",""
        ));
    }

    private String defineBeforeOrAfter(String birthYear){
        if (birthYear.contains("BBY")) return "BBY";
        if (birthYear.contains("ABY")) return "ABY";
        return null;
    }


    private String getTheOldestPersonBby (List<Person> people){
       return people.stream().filter( x-> "BBY".equals(x.getBeforeOrAfter()))
                .max(Comparator.comparing(Person::getAge)).get().getName();
    }

    private String getTheOldestPersonAby (List<Person> people){
        return people.stream().filter( x-> "ABY".equals(x.getBeforeOrAfter()))
                .max(Comparator.comparing(Person::getAge)).get().getName();
    }



    @Test
    public void verifiesThatVaderStarshipsIsAvailableInFilmsList(){

        //Step 1
        Person vader = searchForThePerson("Vader");

        //Step 2
        List <String> films = vader.getFilms();
        String filmWithMinCountPlanet =
                getFilmPlanetsCount(films)
                        .stream()
                        .min(Comparator.comparing(Film::getPlanetCount))
                        .get().getName();


        //Step 3
        List<String> filmsStarships = RestAssured.given()
                .get(filmWithMinCountPlanet)
                .then()
                .extract()
                .jsonPath()
                .getList("starships");

        List <String> vaderStarships = vader.getStarships();

        assertThat(filmsStarships).containsAll(vaderStarships);

    }

    @Test
    public void verifiesTheOldestPerson (){
        //Step 4
        List<Person> people = getPersonsList();

         people.stream()
               .filter(person -> !"unknown".equals(person.getBirthYear()))
                .forEach( x-> {
                x.setAge(convertToAge(x.getBirthYear()));
                x.setBeforeOrAfter(defineBeforeOrAfter(x.getBirthYear()));
        }
        );

        String nameBby = getTheOldestPersonBby(people);

        String theOldestPerson = (Strings.isNullOrEmpty(nameBby)) ?
                getTheOldestPersonAby(people) : nameBby;


        assertThat(theOldestPerson).isEqualTo("Yoda");
    }

    @Test
    public void verifiesSchemaValidation(){

        //TODO:  Since https://swapi.dev/api/people/schema/ returns 404. Json schema was generated by Online generator
        String url = baseUrl + "people";

        InputStream expectedJsonSchema = getClass ().getClassLoader()
                .getResourceAsStream ("people_schema.json");


        RestAssured.given()
                .get(url)
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema (expectedJsonSchema));



    }
}

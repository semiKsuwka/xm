package xm.ui.example.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.google.common.base.Strings;
import org.openqa.selenium.By;
import xm.ui.example.entities.Stock;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class StocksPage {

    private By NorwayBy = By.xpath("//button[@data-value='Norway']");

    private By stocksSeachBy = By.xpath("//input[@ aria-controls='DataTables_Table_0'and @type='search']");

    private By searchResultTableBy= By.xpath("//table[@aria-describedby='DataTables_Table_0_info']");

    public void moveToNorwayStocks(){
        $(NorwayBy).should(visible).click();
    }

    public Stock searchForTheStocks(String description){
        $(stocksSeachBy).should(visible).setValue(description).pressEnter();

        SelenideElement table = $(searchResultTableBy).should(visible);

        table.$$(By.xpath(".//td[@data-xm-qa-name='symbolWithDescription']")).shouldHave(size(1));

        Stock stock = new Stock();
        stock.setSymbolWithDescription( table.$$(
                By.xpath(".//td[@data-xm-qa-name='symbolWithDescription']"))
                .texts().get(0));

        stock.setSymbol( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='symbol']"))
                .texts().get(0));

        stock.setMinSpread( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='minSpread']"))
                .texts().get(0));

        stock.setMinMaxTradeSize( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='minMaxTradeSize']"))
                .texts().get(0));


        stock.setMarginRequirement( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='marginRequirement']"))
                .texts().get(0));

        stock.setSwapLong( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='swapLong']"))
                .texts().get(0));

        stock.setSwapShort( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='swapShort']"))
                .texts().get(0));

        stock.setLimitStopLevel( table.$$(
                        By.xpath(".//td[@data-xm-qa-name='limitStopLevel']"))
                .texts().get(0));

      SelenideElement readMore = table.$$(
                        By.xpath(".//td[@data-xm-qa-name='url']/a"))
                .get(0);

        if (readMore.isDisplayed()) {
            readMore.click(ClickOptions.withTimeout(Duration.ofMinutes(1)));

            return stock;
        }

        table.$$(
                By.xpath(".//td[@data-xm-qa-name='symbolWithDescription']"))
                .get(0).click();

        //Readme in footer
                $(By.xpath("//*[@class='dtr-data']/a"))
                .should(visible)
                        .click(ClickOptions.withTimeout(Duration.ofMinutes(1)));

        return stock;
    }
}

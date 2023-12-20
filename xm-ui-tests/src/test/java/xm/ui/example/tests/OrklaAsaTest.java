package xm.ui.example.tests;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import xm.ui.example.entities.Stock;
import xm.ui.example.pages.StockResultPage;
import xm.ui.example.pages.StocksPage;
import xm.ui.example.pages.menu.items.TradingMenu;
import xm.ui.example.pages.popups.AcceptCookiesPage;
import xm.ui.example.pages.popups.RiskBlockPage;

public class OrklaAsaTest {

    private String baseUrl = "https://www.xm.com/";

    private void validateTheOrklaAsaStocks(){

        new AcceptCookiesPage().clickAcceptAllCookies();

        TradingMenu.STOKES.go();

        new RiskBlockPage().cancelRiskBlock();

        new StocksPage().moveToNorwayStocks();

        Stock expSearchedStock = new StocksPage().searchForTheStocks("Orkla ASA (ORK.OL)");

        Stock actDetailedStock =  new StockResultPage().getStockDetails();

        assertThat(actDetailedStock)
                .usingRecursiveComparison()
                .ignoringFields("symbolWithDescription")
                .isEqualTo(expSearchedStock);

        assertThat(expSearchedStock.getSymbolWithDescription())
                .contains(actDetailedStock.getSymbolWithDescription());

    }

    @AfterEach
    public void teardown (){
        driver().clearCookies();
        driver().close();
    }

    @Test
    public void verifiesTheTradingDataForOrklaAsaStocks_ScreenMax() {
        open(baseUrl);
        getWebDriver().manage().window().maximize();
        validateTheOrklaAsaStocks();
    }

    @Test
    public void verifiesTheTradingDataForOrklaAsaStocks_MediumSize() {
        Configuration.browserSize = "1024x768";
        open(baseUrl);
        validateTheOrklaAsaStocks();
    }

    @Test
    public void verifiesTheTradingDataForOrklaAsaStocks_SmallSize() {
        Configuration.browserSize = "800x600";
        open(baseUrl);
        validateTheOrklaAsaStocks();
    }
}

package xm.ui.example.pages;

import org.openqa.selenium.By;
import xm.ui.example.entities.Stock;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StockResultPage {

    private By stockHeaderBy = By.tagName("h1");

    private By symbolBy= By.xpath("//td[@data-xm-qa-name='symbols__value']");

    private By descriptionBy= By.xpath("//td[@data-xm-qa-name='description__value']");

    private By minMaxTradeSizeBy = By.xpath("//td[@data-xm-qa-name='min_max_trade_size__value']");

    private By swapValueInMarginCurrencyLongBy =
            By.xpath("//td[@data-xm-qa-name='swap_value_in_margin_currency_long__value']");

    private By swapValueInMarginCurrencyShortBy =
            By.xpath("//td[@data-xm-qa-name='swap_value_in_margin_currency_short__value']");

    private By limitAndStopLevelBy =
            By.xpath("//td[@data-xm-qa-name='limit_and_stop_levels__title']/span");

    private By marginRequirementBy =
            By.xpath("//td[@data-xm-qa-name='margin_requirement__value']/span");

    private By spreadsAsLowAsBy =
            By.xpath("//td[@data-xm-qa-name='spreads_as_low_as__value']");

    public Stock getStockDetails(){
        Stock stock = new Stock();

        $(stockHeaderBy).should(visible);

        stock.setSymbol($(symbolBy).getText());
        stock.setSymbolWithDescription($(descriptionBy).getText());
        stock.setMinMaxTradeSize($(minMaxTradeSizeBy).getText());
        stock.setSwapShort($(swapValueInMarginCurrencyShortBy).getText());
        stock.setSwapLong($(swapValueInMarginCurrencyLongBy).getText());
        stock.setLimitStopLevel($(limitAndStopLevelBy).getText());
        stock.setMinSpread($$(spreadsAsLowAsBy).filter(visible).get(0).getText());
        stock.setMarginRequirement($(marginRequirementBy).getText());
        return stock;

    }

}

package xm.ui.example.pages.menu.items;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public abstract class MenuItem {
    protected By mainMenuBy;
    protected By  mainLeftBarBy;
    protected String subMenuItem;

    public void go() {
        clickMainMenuItem();
        clickOnSubMenuItem (subMenuItem);
    }

    private void clickMainMenuItem() {
        if (isCentralMenuVisible()) {
            $(mainMenuBy).should(visible).click();
            return;
        }

        $(By.className("toggleLeftNav")).click();
        $(mainLeftBarBy).should(visible).click();
    }
    private boolean isCentralMenuVisible(){
        return $(mainMenuBy).should(exist).isDisplayed();
    }
    private void clickOnSubMenuItem (String item){
        if (item == null) return;
        String xpath = String.format(item, webdriver().driver().getCurrentFrameUrl());
        $$(By.xpath(xpath)).filterBy(visible).first().click(ClickOptions.withTimeout(Duration.ofMinutes(2)));
    }


}

package xm.ui.example.pages.popups;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AcceptCookiesPage {
    private By acceptCookiesButtonBy = By.xpath("//button[contains(@class, 'acceptDefaultCookieFirstVisit')]");

    public void clickAcceptAllCookies(){
        $(acceptCookiesButtonBy).should(visible).click();
    }
}

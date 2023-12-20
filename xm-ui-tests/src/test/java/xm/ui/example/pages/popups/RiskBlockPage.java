package xm.ui.example.pages.popups;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RiskBlockPage {

    private By riskBlockCancelationButtonBy = By.xpath("//button[@id='js-riskCloseButton']");

    public void cancelRiskBlock(){
        $(riskBlockCancelationButtonBy).should(visible).click();
    }
}

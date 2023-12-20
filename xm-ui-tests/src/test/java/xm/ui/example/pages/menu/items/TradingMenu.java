package xm.ui.example.pages.menu.items;

import org.openqa.selenium.By;

public class TradingMenu extends MenuItem {

    private static By menuCentralBy = By.className("main_nav_trading");

    private static By menuLeftBarBy = By.xpath("//a[@aria-controls='tradingMenu']");

    private static String subMenuStocks ="//*[@id='main-nav']//*[@href='%sstocks']";


    public static TradingMenu MAIN = new TradingMenu(menuCentralBy, menuLeftBarBy);
    public static TradingMenu STOKES = new TradingMenu(menuCentralBy, menuLeftBarBy ,subMenuStocks);

    private TradingMenu (By mainMenuBy, By menuLeftBarBy, String subMenuBy){
        super.mainMenuBy = mainMenuBy;
        super.mainLeftBarBy = menuLeftBarBy;
        super.subMenuItem = subMenuBy;

    }

    private TradingMenu (By mainMenu, By mainLeftBarBy) {

        super.mainMenuBy = mainMenu;
        super.mainLeftBarBy = mainLeftBarBy;
    }

}

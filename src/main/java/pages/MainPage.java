package pages;

import elements.Tables;
import org.openqa.selenium.By;
import utils.PropertiesUtil;

public class MainPage extends DefaultPage {

    public MainPage() {
        driver.get(MAIN_URL);
    }

    private static final String MAIN_URL = PropertiesUtil.get("test.host");
    private static final String TRACK_NUMBER = PropertiesUtil.get("test.tracknumber");
    private static final String TRACK_NUMBERS = PropertiesUtil.get("test.tracknumbermass");

    private static final By trackNumberInputLocator = By.xpath("//input[@id='TxtNumPos']");
    private static final By findButtomLocator = By.xpath("//input[@id='BtnSearch']");
    private static final By casualInfoLocator = By.xpath("//div[@id=\"Panel2\"]//table[1]");
    private static final By rBInfoLocator = By.xpath("//div[@id=\"Panel2\"]/span");
    private static final By rBDataTableLocator = By.xpath("//div[@id=\"Panel2\"]/span/following-sibling::div/table");
    private static final By borderControlInfoLocator = By.xpath("//span//a[contains(@href, \"search.aspx\")]");


    public void findPackages() {
        String[] trackNumbers = TRACK_NUMBERS.split(",");
        for (int i = 0; i < trackNumbers.length; i++) {
            findPackage(trackNumbers[i]);
        }
        driver.quit();
    }

    public void findPackage(String trackNumber) {
//        String trackNumber = TRACK_NUMBER;
        System.out.println("\nFor Mail with track number: " + trackNumber);
        type(trackNumberInputLocator, trackNumber);
        click(findButtomLocator);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isElementPresent(rBInfoLocator)) {
            Tables.getTableInfo(rBDataTableLocator);
        } else if (isElementPresent(borderControlInfoLocator)) {
            BorderControlPage bcpage = openBorderControlPage();
            bcpage.checkState();
        } else  if (isElementPresent(casualInfoLocator)){
            Tables.getTableInfo(casualInfoLocator);
        } else {
            System.out.println("There is no info about such package");
        }
    }


    public BorderControlPage openBorderControlPage() {
        driver.findElement(borderControlInfoLocator).click();
        return new BorderControlPage();
    }


}

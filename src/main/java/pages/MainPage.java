package pages;

import elements.Tables;
import org.openqa.selenium.By;
import utils.PropertiesUtil;

import java.util.List;
import java.util.Map;

public class MainPage extends DefaultPage {

    private static final String MAIN_URL = PropertiesUtil.get("test.host");
    private static final String TRACK_NUMBER = PropertiesUtil.get("test.tracknumber");
    private static final String TRACK_NUMBERS = PropertiesUtil.get("test.tracknumbermass");

    private static final By trackNumberInputLocator = By.xpath("//input[@id='TxtNumPos']");
    private static final By findButtomLocator = By.xpath("//input[@id='BtnSearch']");
    private static final By casualInfoLocator = By.xpath("//div[@id=\"Panel2\"]//table[1]");
    private static final By rBInfoLocator = By.xpath("//div[@id=\"Panel2\"]/span");
    private static final By rBDataTableLocator = By.xpath("//div[@id=\"Panel2\"]/span/following-sibling::div/table");
    private static final By borderControlInfoLocator = By.xpath("//span//a[contains(@href, \"search.aspx\")]");


    public MainPage openBrowserPage() {
        driver.get(MAIN_URL);
        return this;
    }

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
        searchItem(trackNumber);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        decisionWhatToCollect();
    }

    public void searchItem(String trackNumber) {
        type(trackNumberInputLocator, trackNumber);
        click(findButtomLocator);
    }

    public List<Map<String, String>> decisionWhatToCollect() {
        if (isElementPresent(rBInfoLocator)) {
            return collectRBData();
        } else if (isElementPresent(borderControlInfoLocator)) {
            return collectBorderControlData();
        } else if (isElementPresent(casualInfoLocator)) {
            return collectDefaultData();
        } else {
            System.out.println("There is no info about such package");
            return null;
        }
    }

    private List<Map<String, String>> collectDefaultData() {
        return Tables.getRowsWithColumnsByHeadings(casualInfoLocator);
    }

    private List<Map<String, String>> collectBorderControlData() {
        BorderControlPage bcpage = openBorderControlPage();
        return bcpage.getState();
    }

    private List<Map<String, String>> collectRBData() {
        return Tables.getRowsWithColumnsByHeadings(rBDataTableLocator);
    }

    public BorderControlPage openBorderControlPage() {
        driver.findElement(borderControlInfoLocator).click();
        return new BorderControlPage();
    }

    public static void readTableInfo(List<Map<String, String>> tableInfo) {
        for (Map<String, String> event : tableInfo) {
            for (Map.Entry<String, String> entry : event.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }


}

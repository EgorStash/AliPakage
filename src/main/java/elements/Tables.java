package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tables {

    private static WebDriver driver = WebDriverCreator.getWebDriver();

    private static List<WebElement> getRows(WebElement element) {
        List<WebElement> rows = element.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    // Find all Column titles
    private static List<WebElement> getHeading(WebElement element) {
        WebElement headingsRows = element.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headingCounts = headingsRows.findElements(By.xpath(".//th"));
        return headingCounts;
    }

    // Find all cells in table
    private static List<List<WebElement>> getRowsWithColumns(WebElement element) {
        List<WebElement> rows = getRows(element);
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }


    public static List<Map<String, String>> getRowsWithColumnsByHeadings(By locator) {
        WebElement element = driver.findElement(locator);
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns(element);
        List<Map<String, String>> rowsWithColumnsByHeadings = new ArrayList<Map<String, String>>();
        Map<String, String> rowsByHeadings;
        List<WebElement> headingColumns = getHeading(element);

        for (List<WebElement> row : rowsWithColumns) {
            rowsByHeadings = new HashMap<String, String>();
            for (int i = 0; i < headingColumns.size(); i++) {
                String heading = headingColumns.get(i).getText();
                String cell = isElementContainsSpan(row.get(i)).getText();
                rowsByHeadings.put(heading, cell);
            }
            rowsWithColumnsByHeadings.add(rowsByHeadings);
        }
        return rowsWithColumnsByHeadings;
    }

    private static WebElement isElementContainsSpan(WebElement cell) {
        if (!cell.findElements(By.xpath("./span")).isEmpty()) {
            return cell.findElement(By.xpath("./span"));
        }
        return cell;

    }

    public String getValuefromCell(By locator, int rowNumber, String columnName) {
        List<Map<String, String>> rowsWithColumnsByHeadings = getRowsWithColumnsByHeadings(locator);
        String cell = rowsWithColumnsByHeadings.get(rowNumber - 1).get(columnName);
        return cell;
    }

    public String getValuefromCell(By locator, int rowNumber, int columnNumber) {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns(driver.findElement(locator));
        WebElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber - 1);
        return cell.getText();
    }

    public static void getTableInfo(By locator){
        for(Map<String, String> event : getRowsWithColumnsByHeadings(locator)){
            for (Map.Entry<String, String> entry : event.entrySet())
            {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

}

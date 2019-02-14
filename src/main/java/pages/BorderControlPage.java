package pages;

import elements.Tables;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

public class BorderControlPage extends DefaultPage {

    private By borderControlCheckButtonLocator = By.xpath("//input[@id='BtnSearch']");
    private By borderControlDataTableLocator = By.xpath("//table");


    public void checkState(){
        click(borderControlCheckButtonLocator);
        System.out.println("---------------------");
        System.out.println("Border Control Info: ");
        Tables.getTableInfo(borderControlDataTableLocator);
        driver.navigate().back();
        driver.navigate().back();
    }

    public List<Map<String, String>> getState(){
        click(borderControlCheckButtonLocator);
        List<Map<String, String>> result = Tables.getRowsWithColumnsByHeadings(borderControlDataTableLocator);
        driver.navigate().back();
        driver.navigate().back();
        return result;
    }


}

package pages;

import elements.Tables;
import org.openqa.selenium.By;

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


}

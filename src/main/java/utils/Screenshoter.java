package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshoter {

    public static void makeScreen() {
        WebDriver driver = WebDriverCreator.getWebDriver();

//        LocalDateTime dateNow = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD-MM-yyyy HH:mm");
//        String fileName = dateNow + ".png";
        String screenshotName = "Test-" + System.nanoTime();
        String fileName = screenshotName + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\JobScreenshots\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to make screenshot");
        }
    }
}

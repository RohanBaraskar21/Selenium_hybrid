package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) return null;
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String folder = "target/report/screenshots/";
            String fileName = screenshotName + "_" + System.currentTimeMillis() + ".png";
            String destPath = folder + fileName;
            File destFile = new File(destPath);
            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath());
            System.out.println("[DEBUG] Screenshot saved to: " + destFile.getAbsolutePath());
            // Return path relative to report location (target/report/ExtentReport.html)
            return "screenshots/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
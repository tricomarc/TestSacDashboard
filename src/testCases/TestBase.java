package testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestBase {
	
	WebDriver driver;
	
	public TestBase(WebDriver driver) {
		this.driver = driver;
	}
	
	public static void capturaPantalla(WebDriver driver, String fileName) throws IOException {
		
		TakesScreenshot screenShot = ((TakesScreenshot)driver);
		File screenFile = screenShot.getScreenshotAs(OutputType.FILE);
		
		File destFile = new File(fileName);
		FileUtils.copyFile(screenFile, destFile);
	}
}

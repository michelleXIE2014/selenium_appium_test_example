package com.lazycode.appium;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


@Test(groups={"report"})
public class CheckSonarReportTest {
	private WebDriver driver;

	@Test
	public void takeScreenshot() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:9000/overview/debt?id=com.prodigy%3Aautomation_appium_test");
		Thread.sleep(5000);     //NOSONAR
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("target/sonar_report/screenshot_sonar.png"));
        driver.close();
	}

}

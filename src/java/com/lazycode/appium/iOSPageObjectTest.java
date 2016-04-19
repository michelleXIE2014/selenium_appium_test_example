package com.lazycode.appium;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class iOSPageObjectTest {

	private WebDriver driver;
	@FindBy(className = "UIAButton")
	public List<WebElement> uiButtons;

	@FindBy(className = "UIATextField")
	public List<WebElement> text1ClassName;

	@FindBy(name = "IntegerA")
	public List<WebElement> text1CssSelector;

	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
	public List<WebElement> text1Xpath;

	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATextField[2]")
	public List<WebElement> text2Xpath;

	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")
	public List<WebElement> computeButton;

	@FindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]")
	public List<WebElement> answer;

	@BeforeMethod
	public void setUp() throws Exception {
		File appDir = new File(System.getProperty("user.dir"), "src/test/resources/");
		File app = new File(appDir, "TestApp.app");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "Simulator");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				"iPhone 5 (9.3) [DF27470F-EE7D-4E80-95CC-60E2C35FE969]");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void findByElementsTest() {
		System.out.println("uiButton size=" + uiButtons.size());
		System.out.println("text1 classname size=" + text1ClassName.size());
		System.out.println("text1 css selector size=" + text1CssSelector.size());
		System.out.println("text1 xpath size=" + text1Xpath.size());
		text1Xpath.get(0).sendKeys("12");
		text2Xpath.get(0).sendKeys("34");
		uiButtons.get(0).click();
		System.out.println("anwser = " + answer.get(0).getText());
		Assert.assertTrue(answer.get(0).getText().equals("46"));
	}

}

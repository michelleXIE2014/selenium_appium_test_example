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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidPageObjectTest {

	private WebDriver driver;
	@FindBy(className = "android.widget.TextView")
	public List<WebElement> accessiblityLocatorsClassName;

	@FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[1]")
	public List<WebElement> accessiblityLocators2Xpath;

	@FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[1]")
	public List<WebElement> accessiblityNodeProviderLocators2Xpath;
	@FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
	public List<WebElement> accessiblityNodeProviderDetailLocators2Xpath;

	@BeforeMethod
	public void setUp() throws Exception {
		File appDir = new File("src/test/resources/");
		File app = new File(appDir, "ApiDemos-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void findByElementsTest() {
		System.out.println("classname size=" + accessiblityLocatorsClassName.size());
		System.out.println("xpath size=" + accessiblityLocators2Xpath.size());
		accessiblityLocators2Xpath.get(0).click();
		System.out.println("xpath size=" + accessiblityNodeProviderLocators2Xpath.size());
		System.out.println("button name = " + accessiblityNodeProviderLocators2Xpath.get(0).getText());
		accessiblityNodeProviderLocators2Xpath.get(0).click();
		System.out.println("xpath size=" + accessiblityNodeProviderDetailLocators2Xpath.size());
		System.out.println("button name = " + accessiblityNodeProviderDetailLocators2Xpath.get(0).getText());
		Assert.assertTrue(accessiblityNodeProviderDetailLocators2Xpath.get(0).getText().contains(
				"Enable TalkBack and Explore-by-touch from accessibility settings. Then touch the colored squares."));
	}

}

package com.lazycode.appium;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

@Test(groups={"remote"})
public class AndroidPageObjectSLTest implements SauceOnDemandSessionIdProvider{

	final private String USERNAME = "MichelleXIE2016";
	final private String ACCESS_KEY = "accessKey";
	private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);

	private AndroidDriver<WebElement> driver;
	private String sessionId;

	public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@FindBy(className = "android.widget.TextView")
	public List<WebElement> accessiblityLocatorsClassName;

	@FindBy(className = "android.widget.TextView")
	public List<WebElement>  accessiblityNodeProviderLocatorsClassName;

	@FindBy(className = "android.widget.TextView")
	public List<WebElement> accessiblityNodeProviderDetailLocatorsClassName;

	
	@FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[1]")
	public List<WebElement> accessiblityLocators2Xpath;

	@FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[1]")
	public List<WebElement> accessiblityNodeProviderLocators2Xpath;
	
	@FindBy(xpath = "//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
	public List<WebElement> accessiblityNodeProviderDetailLocators2Xpath;

	@BeforeMethod
	public void setUp() throws Exception {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				"https://github.com/SMARTeacher/automation_appium_tests/blob/master/src/test/resources/ApiDemos-debug.apk?raw=true");
		desiredCapabilities.setCapability("appiumVersion", "1.5.1");
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy Tab 3 Emulator");
		desiredCapabilities.setCapability("deviceOrientation", "portrait");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.2");
		// desiredCapabilities.setCapability("name", name.getMethodName());

		URL sauceUrl = new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey()
				+ "@ondemand.saucelabs.com:80/wd/hub");

		driver = new AndroidDriver<>(sauceUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sessionId = driver.getSessionId().toString();
		System.out.println("session ID =" + sessionId);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		System.out.println("Link to your job: https://saucelabs.com/jobs/" + this.getSessionId());
        driver.quit();
	}

	@Test
	public void findByElementsTest() {
		System.out.println("classname size=" + accessiblityLocatorsClassName.size());
		System.out.println("xpath size=" + accessiblityLocators2Xpath.size());
		System.out.println("button name = " + accessiblityNodeProviderLocatorsClassName.get(1).getText());
		accessiblityLocatorsClassName.get(1).click();
		System.out.println("xpath size=" + accessiblityNodeProviderLocatorsClassName.size());
		System.out.println("button name = " + accessiblityNodeProviderLocatorsClassName.get(1).getText());
		accessiblityNodeProviderLocatorsClassName.get(1).click();
		System.out.println("xpath size=" + accessiblityNodeProviderDetailLocatorsClassName.size());
		System.out.println("button name = " + accessiblityNodeProviderDetailLocatorsClassName.get(1).getText());
		
		
		Assert.assertTrue(accessiblityNodeProviderDetailLocatorsClassName.get(1).getText().contains(
				"Enable TalkBack and Explore-by-touch from accessibility settings. Then touch the colored squares."));
	}

}

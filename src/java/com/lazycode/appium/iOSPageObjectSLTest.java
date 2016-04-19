package com.lazycode.appium;

import java.net.MalformedURLException;
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

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

@Test(groups={"remote"})
public class iOSPageObjectSLTest implements SauceOnDemandSessionIdProvider {
	final private String USERNAME = "MichelleXIE2016";
	final private String ACCESS_KEY = "accessKey";
	private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);

	private IOSDriver<WebElement> driver;
	private String sessionId;

	public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				"https://github.com/SMARTeacher/automation_appium_tests/blob/master/src/test/resources/TestApp.zip?raw=true");
		desiredCapabilities.setCapability("appiumVersion", "1.5.1");
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
		desiredCapabilities.setCapability("deviceOrientation", "portrait");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
		// desiredCapabilities.setCapability("name", name.getMethodName());

		URL sauceUrl = new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey()
				+ "@ondemand.saucelabs.com:80/wd/hub");

		driver = new IOSDriver<>(sauceUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sessionId = driver.getSessionId().toString();
		System.out.println("session ID =" + sessionId);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("Link to your job: https://saucelabs.com/jobs/" + this.getSessionId());
		driver.quit();
	}

	@FindBy(className = "UIAButton")
	public List<WebElement> uiButtons;

	@FindBy(className = "UIATextField")
	public List<WebElement> text1ClassName;

	@FindBy(className = "UIAStaticText")
	public List<WebElement> answerClassName;

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

	@Test
	public void findByElementsTest() {
		System.out.println("uiButton size=" + uiButtons.size());
		System.out.println("text1 classname size=" + text1ClassName.size());
		System.out.println("text1 css selector size=" + text1CssSelector.size());
		System.out.println("text1 xpath size=" + text1Xpath.size());
		text1ClassName.get(0).sendKeys("12");
		text1ClassName.get(1).sendKeys("34");
		uiButtons.get(0).click();
		System.out.println("anwser = " + answerClassName.get(0).getText());
		Assert.assertTrue(answerClassName.get(0).getText().equals("46"));
	}

}

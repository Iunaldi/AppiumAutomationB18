package com.cybertek.tests;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CalculatorTests {
    AndroidDriver<AndroidElement> driver;
    private final static Logger logger = Logger.getLogger(CalculatorTests.class);

    /**
     * {
     * "platformName": "Android",
     * "deviceName": "Pixel_2",
     * "appPackage": "com.android.calculator2",
     * "platformVersion": "7.0",
     * "fullReset": "true",
     * "automationName": "UiAutomator2",
     * "newCommandTimeout": "7200",
     * "appActivity": "com.android.calculator2.Calculator"
     * }
     */
    @Before
    public void setup() throws Exception {
//        CapabilityType.PLATFORM_NAME == "platformName"
//        MobileCapabilityType.AUTOMATION_NAME == "automationName"
//        AndroidMobileCapabilityType.APP_ACTIVITY == "appActivity"
//      mandatory: platformName, deviceName, platformVersion, automationName, ( (appPackage && appActivity) || app || browserName)
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Pixel_2");
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        desiredCapabilities.setCapability("platformVersion", "7.0");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("newCommandTimeout", "7200");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        //http://localhost:4723/wd/hub - appium server address
    }

    @Test
    public void test1() {
        System.out.println("Test 1");
        logger.info("Test 1");

        AndroidElement btn2 = driver.findElementById("com.android.calculator2:id/digit_2");
        AndroidElement equals = driver.findElementByAccessibilityId("equals");
        AndroidElement plus = driver.findElementByAccessibilityId("plus");
        AndroidElement result = driver.findElementById("com.android.calculator2:id/result");

        btn2.click(); //2
        plus.click(); //+
        btn2.click(); //2
        equals.click();// =

        String actualResult = result.getText();

        Assert.assertEquals("4", actualResult);
    }

    @Test
    public void test2() {
//        WebElement for everything
//        MobileElement for IOS and Android
//        AndroidElement only for android
//        IOSElement only for ios elements
        MobileElement btn7 = driver.findElement(MobileBy.id("com.android.calculator2:id/digit_7"));
        MobileElement btn8 = driver.findElement(MobileBy.id("com.android.calculator2:id/digit_8"));
//        MobileElement divide = driver.findElement(MobileBy.AccessibilityId("divide"));
        MobileElement divide = driver.findElementByAccessibilityId("divide");
        MobileElement result = driver.findElementById("com.android.calculator2:id/result");
        MobileElement equals = driver.findElementByAccessibilityId("equals");


        TouchAction touchActions = new TouchAction(driver);

//        btn7.click();
//        divide.click();
//        btn8.click();
//        equals.click();
        touchActions.tap(new TapOptions().withElement(new ElementOption().withElement(btn7))).perform();
        touchActions.tap(new TapOptions().withElement(new ElementOption().withElement(divide))).perform();
        touchActions.tap(new TapOptions().withElement(new ElementOption().withElement(btn8))).perform();
        touchActions.tap(new TapOptions().withElement(new ElementOption().withElement(equals))).perform();

        String resultValue = result.getText();
        logger.info("Result is: " + resultValue);
    }

    @After
    public void tearDown() {
        driver.closeApp();
    }
}

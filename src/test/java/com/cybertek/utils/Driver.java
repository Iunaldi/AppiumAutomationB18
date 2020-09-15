package com.cybertek.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private static AppiumDriver<MobileElement> driver;
    private static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    private final static String ANDROID_APP_URL = ConfigurationReader.getProperty("android.app.url");
    private final static Logger logger = Logger.getLogger(Driver.class);

    private Driver() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            String platform = ConfigurationReader.getProperty("platform");
            logger.info("Running tests on: " + platform);
            switch (platform) {
                case "android":
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.APP, ANDROID_APP_URL);
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    try {
                        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "ios":
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
//                    we need to have ipa app for IOS, not apk
//                    desiredCapabilities.setCapability(MobileCapabilityType.APP, ANDROID_APP_URL);
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Iphone X");
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    try {
                        driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    logger.error("Driver type is not implemented yet!");
                    throw new RuntimeException("Driver type is not implemented yet!");
            }
        }
        return driver;
    }
}

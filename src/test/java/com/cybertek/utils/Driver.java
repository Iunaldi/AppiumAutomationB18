package com.cybertek.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

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
            logger.info("Running tests on: "+platform);
            switch (platform) {
                case "android":
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.APP, ANDROID_APP_URL);
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    driver = new AndroidDriver<>(desiredCapabilities);
                    break;
                default:
                    logger.error("Driver type is not implemented yet!");
                    throw new RuntimeException("Driver type is not implemented yet!");
            }
        }
        return driver;
    }
}

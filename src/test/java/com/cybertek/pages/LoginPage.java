package com.cybertek.pages;

import com.cybertek.utils.ConfigurationReader;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.log4j.Logger;

public class LoginPage extends BasePage {
    private final static Logger logger = Logger.getLogger(LoginPage.class);

    @AndroidFindBy(id = "com.etsy.android:id/edit_username")
    public AndroidElement emailElement;

    @AndroidFindBy(id = "com.etsy.android:id/edit_password")
    public AndroidElement passwordElement;

    @AndroidFindBy(id = "com.etsy.android:id/button_signin")
    public AndroidElement signInBtnElement;

    @AndroidFindBy(xpath = "//*[@text=\"What's your style?\"]")
    public AndroidElement whatsYourStyleMsg;

    public void login() {
        String email = ConfigurationReader.getProperty("email");
        String password = ConfigurationReader.getProperty("password");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Login with email: " + email);
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        signInBtnElement.click();
    }
}

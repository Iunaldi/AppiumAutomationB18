package com.cybertek.pages;

import com.cybertek.utils.Driver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    @AndroidFindBy(id = "com.etsy.android:id/btn_link")
    public AndroidElement getStartedElement;

    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver()), this);
    }
}

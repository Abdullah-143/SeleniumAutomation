package org.qpros.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage{
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @FindBy(xpath="//h3[text()='Billing Details']")
    WebElement billingDetailsTitle;

    public String getBillingDetailsFormTitle(){
        return billingDetailsTitle.getText();
    }
}

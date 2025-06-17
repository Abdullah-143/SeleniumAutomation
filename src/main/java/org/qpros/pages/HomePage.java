package org.qpros.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage {
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    //********************** Starts Static Locators ************************
    @FindBy(xpath = "//div[contains(@class,'themify_builder_sub_row clearfix gutter-default   sub_row_1-0-2')]")
    public WebElement newArrivalSection;

    @FindBy(xpath = "//a[@title='View your shopping cart']")
    public WebElement shoppingCart;

    //********************** Ends Static Locators ************************


    // ********************** Starts Dynamic Locators ******************************

    /**
     *
     * @param productName
     * @return
     */
    public WebElement getProductLocatorByName(String productName) {
        String dynamicXPath = "//ul[@class='products']/descendant::h3[contains(text(),'" + productName + "')]";
        return driver.findElement(By.xpath(dynamicXPath));
    }

    public WebElement getAddToBasketLocatorByName(String productName) {
        String dynamicXPath = "//ul[@class='products']/descendant::h3[contains(text(),'" + productName + "')]/parent::a/following-sibling::a";
        return driver.findElement(By.xpath(dynamicXPath));
    }

    public WebElement getDiscountedPriceLocatorByName(String productName) {
        String dynamicXPath = "//h3[contains(text(),'" + productName + "')]/following-sibling::span/ins/span";
        return driver.findElement(By.xpath(dynamicXPath));
    }

    // ********************** Ends Dynamic Locators ******************************

    public boolean isProductPresent(String productName){
        actionsScrollToElement(newArrivalSection);
        if(getProductLocatorByName(productName).isDisplayed())
            return true;
        else
            return false;
    }

    public String getProductName(String productName) {
        return getProductLocatorByName(productName).getText();
    }
    public String getProductDiscountedPrice(String productName) {
        return getDiscountedPriceLocatorByName(productName).getText();
    }


    public String clickProductByName(String productName) {
        return getProductLocatorByName(productName).getText();
    }

    public boolean isAddToBasketButtonPresent(String productName){
        if(getAddToBasketLocatorByName(productName).isDisplayed())
            return true;
        else
            return false;
    }

    public void clickOnAddToBasket(String productName){
        wait.until(ExpectedConditions.elementToBeClickable(getAddToBasketLocatorByName(productName))).click();
    }

    public void clickShoppingCart(){
        shoppingCart.click();
    }

}

package org.qpros.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage extends BasePage{

    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @FindBy(xpath="//td[@class='product-name']/a")
    WebElement productName;

    @FindBy(xpath="//td[@class='product-price']/span")
    WebElement productPrice;

    @FindBy(xpath="//td[@class='product-quantity']/div/input")
    WebElement productQuantity;

    @FindBy(xpath="//td[@class='product-subtotal']/span")
    WebElement subTotal;

    @FindBy(xpath="//td[@data-title='Tax']/span")
    WebElement tax;

    @FindBy(xpath="//td[@data-title='Total']/strong/span")
    WebElement total;

    @FindBy(xpath="//a[contains(text(),'Proceed to Checkout')]")
    WebElement proceedToCheckoutButton;

    public String getProductName(){
        return productName.getText();
    }

    public int getPrice(){
        return fetchPriceFromText(productPrice.getText());
    }

    public int getQuantity(){
        return Integer.parseInt(productQuantity.getAttribute("value"));
    }

    public int getSubTotal(){
        return fetchPriceFromText(subTotal.getText());
    }

    public int getTax(){
        return fetchPriceFromText(tax.getText());
    }

    public int getTotal(){
        return fetchPriceFromText(total.getText());
    }

    public void clickOnProceedToCheckoutButton(){
        proceedToCheckoutButton.click();
    }

    public static int fetchPriceFromText(String amount){
        String numericOnly = amount.replaceAll("[^\\d.]", "");
        double price = Double.parseDouble(numericOnly);
        return (int) price;
    }
}

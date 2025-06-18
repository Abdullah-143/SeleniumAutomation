package tests;

import org.qpros.pages.BasketPage;
import org.qpros.pages.CheckoutPage;
import org.qpros.pages.HomePage;
import org.qpros.testbase.TestBase;
import org.qpros.utility.AssertUtils;
import org.testng.annotations.*;

import static org.qpros.pages.BasketPage.fetchPriceFromText;
import static org.qpros.utility.ExtentReportManager.getTest;
import static org.qpros.utility.PropertiesFileUtil.getProperty;

public class TestAssement extends TestBase {
    AssertUtils assertUtils;
    private static final String homePageTestData = System.getProperty("user.dir") + "/src/main/resources/testdata/HomePage.properties";
    private static final String checkoutPageTestData = System.getProperty("user.dir") + "/src/main/resources/testdata/CheckoutPage.properties";


    @Test()
    public void TestCase_01() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver());
        BasketPage basketPage = new BasketPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        assertUtils = new AssertUtils(getTest());
        String productName = getProperty(homePageTestData, "product_name");


        assertUtils.assertTrue(homePage.isProductPresent(productName),
                "Product "+ productName +" is not available on home page");
        String ProductName = homePage.getProductName(productName);
        String DiscountProductPrice = homePage.getProductDiscountedPrice(productName);


        assertUtils.assertTrue(homePage.isAddToBasketButtonPresent(productName),
                "Add to basket button is not visible on home page");
        homePage.clickOnAddToBasket(productName);
        Thread.sleep(5000);
        homePage.clickShoppingCart();
        Thread.sleep(5000);

        assertUtils.assertEquals(ProductName, basketPage.getProductName(),
                "Product name on basket page does not match with the product name on home page");
        assertUtils.assertEquals(fetchPriceFromText(DiscountProductPrice), basketPage.getPrice(),
                "Product price on basket page does not match with the product price on home page");
        assertUtils.assertEquals(basketPage.getSubTotal(), basketPage.getPrice() * basketPage.getQuantity(),
                "sub-total amount calculation is incorrect");
        int total = basketPage.getSubTotal() + basketPage.getTax();
        assertUtils.assertEquals(basketPage.getTotal(), total,
                "total amount calculation is incorrect");
        basketPage.clickOnProceedToCheckoutButton();

        Thread.sleep(5000);
        assertUtils.assertEquals(checkoutPage.getBillingDetailsFormTitle(), getProperty(checkoutPageTestData, "billing_details_form_title"),
                "user is not redirected to billing details page");

    }
}

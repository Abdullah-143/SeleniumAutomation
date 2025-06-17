package org.qpros.testbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    String websiteUrl = null;
    public static Properties prop = new Properties();
    private String configFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    public WebDriver getDriver() {
        return driver.get();
    }

    {
        FileInputStream fis;
        try {
            fis = new FileInputStream(configFilePath);
            prop.load(fis);
        } catch (Exception e) {
            System.out.println("Exception caught while reading config file " + e.getMessage());
        }
    }

    @Parameters("browser")
    @BeforeMethod(enabled = true)
    public void initialSetUp(@Optional("firefox") String browser) {
        try {
            launchBrowser(browser);
            websiteUrl = prop.getProperty("website_url");
            getDriver().get(websiteUrl);
        } catch (Exception e) {
            System.out.println("Exception caught while reading properties from config file " + e.getMessage());
        }
    }

    /**
     * 'launchBrowser' method is used to launch browser (chrome using
     * WebDriverManager) based on the values provided in the property file and return
     * the driver instance
     * @author Abdullah Shaikh
     */
    public WebDriver launchBrowser(String browserName) {
        System.out.println("Launching Browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }
        getDriver().manage().window().maximize();
        return getDriver();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

}

package org.qpros.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qpros.testbase.TestBase;

public class BasePage {

        protected WebDriver driver;

        /**
         * super class constructor to set driver and yaml object repo
         *
         * @param driver
         */
        public BasePage(WebDriver driver) {
            this.driver = driver;
        }


        /**
         * executes javscript by passing element
         *
         * @param script
         * @param element
         * @return object
         */
        protected Object jsExecuteScript(String script, WebElement element) {
            TestBase testBase = new TestBase();
            Object scriptExceute = null;
            try {
                JavascriptExecutor js = (JavascriptExecutor) testBase.getDriver();
                scriptExceute = js.executeScript(script, element);
            } catch (Exception e) {
                throw e;
            }
            return scriptExceute;
        }

        /**
         * scrolls to element by action class
         *
         * @param element
         */
        protected void actionsScrollToElement(WebElement element) {
            try {
                jsExecuteScript("arguments[0].scrollIntoView();", element);
            } catch (Exception e) {
                throw e;
            }
        }
    }



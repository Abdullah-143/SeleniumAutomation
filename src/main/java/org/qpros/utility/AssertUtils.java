package org.qpros.utility;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;

public class AssertUtils {

    private ExtentTest test ;

    public AssertUtils(ExtentTest test) {
        this.test = test;
    }

    public void assertEquals(Object actual, Object expected, String stepDescription) {
        try {
            Assert.assertEquals(actual, expected);
            test.pass(stepDescription + " | Expected: " + expected + ", Actual: " + actual);
        } catch (AssertionError e) {
            test.fail(stepDescription + " | Expected: " + expected + ", Actual: " + actual);
            throw e;
        }
    }

    public void assertTrue(boolean condition, String stepDescription) {
        try {
            Assert.assertTrue(condition);
            test.pass(stepDescription);
        } catch (AssertionError e) {
            test.fail(stepDescription + " | Assertion failed");
            throw e;
        }
    }

    public void assertFalse(boolean condition, String stepDescription) {
        try {
            Assert.assertFalse(condition);
            test.pass(stepDescription);
        } catch (AssertionError e) {
            test.fail(stepDescription + " | Assertion failed");
            throw e;
        }
    }

    // Add more custom wrappers as needed (assertNotNull, assertNull, etc.)
}

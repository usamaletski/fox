package com.selenium.test.testng.tests.desktop;

import com.selenium.test.utils.DNModel;
import com.selenium.test.utils.DNUtils;
import com.selenium.test.webtestsbase.WebDriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

/**
 * Base class for all tests
 */
public class BaseTest {

    public static DNModel dn;

    protected Assertion hardAssert = new Assertion();
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void baseSetUp() throws Exception {

        System.out.println("INFO: Setting up BaseTest..");

        WebDriverFactory.startBrowser(true);
        DNUtils.init();
        dn = DNModel.getInstance();
        dn.init();

        System.out.println("Setting up " + BaseTest.this.getClass().getSimpleName() + "...");
    }

    @AfterTest
    public void baseCleanUp() throws MalformedURLException {
        WebDriverFactory.finishBrowser();
    }

}
package testDefinition;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import supportMethods.FileRead;
import supportMethods.GenerateData;
import testRunner.TestRunner;

import java.io.IOException;

public class BaseTest {
    protected Assertion hardAssert = new Assertion();
    protected SoftAssert softAssert = new SoftAssert();
    GenerateData genData;


    @BeforeTest()
    public void beforeAll() throws IOException {

        TestRunner.config = FileRead.applicationProperties();
        genData = new GenerateData();
    }

    @AfterTest
    public void after() {
    }
}
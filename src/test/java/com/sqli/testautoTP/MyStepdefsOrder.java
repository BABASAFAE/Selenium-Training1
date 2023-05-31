package com.sqli.testautoTP;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sqli.testauto.BasketPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;


public class MyStepdefsOrder {

    private WebDriver driver;
    ExtentTest test;
    ExtentReports reports;
    String browserName="chrome";



    @Given("User opens the url {string}")
    public void userOpensTheUrl(String arg0) throws IOException {
        Base base=new Base(driver,browserName,test,reports);
        driver =base.setUpWebDriver();
        base.setUpReport();
        test = base.getTest();
        reports = base.getReport();

    }

    @When("User scrolls to  {} capsule")
    public void userScrollsToCapsule(String arg0) {
        OriginalNespressoPage originalNespressoPage= new OriginalNespressoPage(driver);
        originalNespressoPage.lookingForProduct();


    }

    @And("User adds {} units of {} capsule to cart")
    public void userAddsUnitsOfCapsuleToCart(String arg0, String arg1) throws InterruptedException {
        OriginalNespressoPage originalNespressoPage= new OriginalNespressoPage(driver);
        originalNespressoPage.addProduct();

    }

    @Then("Mini cart contains {} units of {} capsule")
    public void miniCartContainsUnitsOfCapsule(String quantity, String productName) {
   Basket basket=new Basket(driver,test);
   basket.checkProduct(productName);

    }
    @After
    public void afterTest(){
        Base base= new Base(driver,browserName,test,reports);
        base.afterTestSetUp();
    }
}

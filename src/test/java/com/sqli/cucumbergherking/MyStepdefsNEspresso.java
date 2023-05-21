package com.sqli.cucumbergherking;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sqli.testauto.Base;
import com.sqli.testauto.BasketPage;
import com.sqli.testauto.HomePage;
import com.sqli.testauto.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MyStepdefsNEspresso {
    WebDriver driver;
    ExtentTest test;
    ExtentReports report;
    String browserName="chrome";
    @Given("^the user is on the  Nespresso homepage$")
    public void the_user_is_on_the_nespresso_homepage() throws Throwable {
        //class instantiation
        Base base=new Base(driver,browserName);
        //setup webdriver
        driver = base.setUpWebDriver();
        base.setUpReport();
    }
    @When("the user selects {} from the {} menu")
    public void theUserSelectsFromTheMenu(String article, String type) throws InterruptedException {
        HomePage homePage =new HomePage(driver);
        homePage.ChooseArticle(type,article);
    }
    @And("adds it to the basket")
    public void addsItToTheBasket() throws InterruptedException {
        HomePage homePage =new HomePage(driver);
        homePage.AddToBasket();
    }
    @Then("the product  {}  should be listed in the basket {}")
    public void theProductShouldBeListedInTheBasket(String productTitle ,String productName) throws InterruptedException {
        BasketPage basketPage =new BasketPage(driver,test);
        basketPage.checkProductIntoBasket(productTitle,productName);
    }
    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        LoginPage loginPage=new LoginPage(driver,test);
        loginPage.proceedToCheckout();
    }

    @Then("the login page displays {}")
    public void theLoginPageDisplays(String productName) throws InterruptedException {
        LoginPage loginPage=new LoginPage(driver,test);
        loginPage.checkDisplayLoginPage(productName);
    }
@After
public void afterTest() {
    //AfterTest afterTest=new AfterTest(driver);
    Base base=new Base(driver,browserName);
    base.afterTestSetUp();


}




}

package com.sqli.testauto;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.util.ArrayList;

public class Traitement {
    static ExtentTest test;
    static ExtentReports report;
    WebDriver driver;
    ArrayList<Product> arrayList;
    String browserName="chrome";

    @Before
    public void setUp() throws IOException {
        //class instantiation Base
        Base base=new Base(driver,browserName);
        //convert json to array
        arrayList = base.convertJsonToArrayList();
        //setup report
        base.setUpReport();
        //setup webdriver
        driver = base.setUpWebDriver();
        test = base.getTest();
        report = base.getReport();
    }

    @Test
    public void traitementNesspressoAddProduct() throws IOException, InterruptedException {
        //loop and get data from json file

        for (int i = 0; i < arrayList.size(); i++) {

            //class instantiation from choose article to add it into basket
            HomePage homePage = new HomePage(driver);
            //class instantiation open and check the basket
            BasketPage basketPage = new BasketPage(driver, test);
            //class instantiation check login
            LoginPage loginPage = new LoginPage(driver,test);
            //choose article from menu to article
            homePage.ChooseArticle( arrayList.get(i).getType(), arrayList.get(i).getArticle());
            //add article into basket
            homePage.AddToBasket();
            //Check product name
            basketPage.checkProductIntoBasket( arrayList.get(i).getproductTitle(), arrayList.get(i).getName());
            //second step check when I click in proced checkout btton then the site redirect destination to login page
            loginPage.proceedToCheckout();
            loginPage.checkDisplayLoginPage(arrayList.get(i).getName());
            //navigate to the website
            driver.navigate().to("https://www.nespresso.com/fr/fr/");

        }

    }

    @After
    public void afterTest() {
        //AfterTest afterTest=new AfterTest(driver);
        Base base=new Base(driver,browserName);
        base.afterTestSetUp();

    }

}



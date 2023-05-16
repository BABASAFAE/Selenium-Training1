package com.sqli.testauto;
import java.io.IOException;
import java.util.ArrayList;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TraitementJson  {
    static ExtentTest test;
    static ExtentReports report;
    WebDriver driver;
    ArrayList<Catalogue>arrayList;

@Before
public void setUp() throws IOException {
        //class instantiation beforeTest
        BeforeTest beforeTest=new BeforeTest();
        //convert json to array
        arrayList=  beforeTest.convertJsonToArrayList();
        //setup report
        beforeTest.setUpReport();
        //setup webdriver
        driver =beforeTest.setUpWebDriver();
        test =beforeTest.getTest();
        report= beforeTest.getReport();
}

@Test
public void traitementNesspressoAddProduct() throws IOException, InterruptedException {
        //loop and get data from json file

    for(int i=0; i< arrayList.size();i++){
        //class instantiation Page Factory
        HomePage homePage=new HomePage(driver);
        //class instantiation from choose article to add it into basket
        OrderPageCf1 orderPageCf1=new OrderPageCf1(driver);
        //class instantiation open and check the basket
        BasketPage basketPage =new BasketPage(driver);
        //class instantiation check login
        LoginPage loginPage=new LoginPage(driver);
        //choose article from menu to article
        orderPageCf1.ChooseArticle(driver,arrayList.get(i).getType(),arrayList.get(i).getArticle());
        //add article into basket
        orderPageCf1.AddToBasket(driver);
        //Check product name
        basketPage.procedToCheckout(driver,arrayList.get(i).getproductTitle(),arrayList.get(i).getName(),test);
        //second step check when I click in proced checkout btton then the site redirect destination to login page
        loginPage.checkLogin(driver,arrayList.get(i).getName(),test);
        //navigate to the website
        driver.navigate().to("https://www.nespresso.com/fr/fr/");

    }

    }


    @After
    public  void afterClass() {
        BeforeTest beforeTest=new BeforeTest();
        beforeTest.afterClass();
    }

    public static void main(String[] args) throws IOException{

    }

    }



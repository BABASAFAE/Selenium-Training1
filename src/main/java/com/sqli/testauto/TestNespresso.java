package com.sqli.testauto;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestNespresso {
    WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;
  @Before
  public void setUpWebDriver()  {

      //setUp chromedriver
      System.setProperty("webdriver.chrome.driver","src/webDriver/geckodriver.exe");
      driver = new FirefoxDriver();

  } @Before
    public void setUpReport(){
        //report
        report = new ExtentReports(System.getProperty("user.dir") + "//NespressoReportResults.html");
        test = report.startTest("nespressoReportsDemo");
    } @Before
    public void setUpReadPropertiesFile()throws IOException {
        //read file webdriver.properties
        FileReader reader=new FileReader("webdriver.properties");
        Properties prop=new Properties();
        prop.load(reader);
        System.out.println("username: "+ prop.getProperty("chrome"));
        System.out.println("password: "+ prop.getProperty("firefox"));

    } @Before
    public void HandleAccessToHomePage(){
        //access to nespresso website
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/fr");

        //remove cookies (solve problem access denied to the site)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@id='_evidon-barrier-declinebutton']")).click();
        driver.manage().deleteAllCookies();

    }
     @Test
     public void TreatmentNespresso() throws InterruptedException {
        OrderPageCf orderPageCf=new OrderPageCf();
         BasketPage basketPage =new BasketPage();
         LoginPage loginPage=new LoginPage();
         //first step check if the product added into basket using extent report include two methods
         //Go to article that I want to add into basket
         orderPageCf.ChooseArticle(driver);
         orderPageCf.AddToBasket(driver);
         String productInBasketTitle=basketPage.procedToCheckout(driver);
         if(productInBasketTitle.contains("Jamaica Blue Mountain"))
         {test.log(LogStatus.PASS, "the same product added");}
         else
         {test.log(LogStatus.FAIL, "Test Failed product added");}
         //second step check when I click in proced checkout btton then the site redirect destination to login page
        String titlepagelogin=loginPage.checkLogin(driver);
         if(titlepagelogin.contains("SE CONNECTER")||titlepagelogin.contains("LOG IN"))
        {test.log(LogStatus.PASS, "login page appears");}
         else
        {test.log(LogStatus.FAIL, "Test Failed login page");}
       System.out.println("traitement"+driver.getTitle()+ "productInBasketTitle "+productInBasketTitle );
}

    @Test
    public void test(){

    }
     @After
    public void close(){
         report.endTest(test);
         report.flush();
         //driver.quit();
     }
}

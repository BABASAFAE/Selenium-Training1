package com.sqli.testauto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TraitementJson  {
    static ExtentTest test;
    static ExtentReports report;
    WebDriver driver;
    //json
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode datafake = objectMapper.readTree(new File("src/webDriver/testDtata.json"));
    String json = datafake.toString();
    Gson gson = new Gson();
    Catalogue[] catalogueArray = gson.fromJson(json, Catalogue[].class);
    ArrayList<Catalogue> arrayList = new ArrayList<>(Arrays.asList(catalogueArray));

    public TraitementJson() throws IOException {
    }


    @Before
    public void setUpReport() throws IOException {
        //report
        report = new ExtentReports(System.getProperty("user.dir") + "//NesspressoReportResults1.html");
        test = report.startTest("nespressoReportsDemo1");
    }
    @Before
    public void setUpWebDriver(){
        //setupdriver firefox

       /* System.setProperty("webdriver.firefox.driver","src/webDriver/geckodriver.exe");
        FirefoxOptions  options= new FirefoxOptions();
       // options.addPreference("dom.webdriver.enabled", false);
        options.setHeadless(false);
        FirefoxProfile firefoxProfile= new FirefoxProfile();
        firefoxProfile.setPreference("dom.webnotifications.enabled", false);
        options.setProfile(firefoxProfile);
        driver = new FirefoxDriver(options);
        driver.get("https://www.nespresso.com/fr/fr/");*/

        //Setup chrome driver
        System.setProperty("webdriver.chrome.driver","src/webDriver/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/fr/");
        driver.manage().deleteAllCookies();
    }



@Test
public void traitementNesspressoAddProduct() throws IOException, InterruptedException {


    System.out.println(arrayList);
    for(int i=0; i< arrayList.size();i++){
        OrderPageCf1 orderPageCf1=new OrderPageCf1();
        BasketPage basketPage =new BasketPage();
        LoginPage loginPage=new LoginPage();
        String type=arrayList.get(i).getType();
        System.out.println("machine type :" + type);
        orderPageCf1.ChooseArticle(driver,arrayList.get(i).getType(),arrayList.get(i).getArticle());
        orderPageCf1.AddToBasket(driver);
        System.out.println("size : "+arrayList.size());
        String productInBasketTitle=basketPage.procedToCheckout(driver,arrayList.get(i).getproductTitle());
        //Check product name
        if(productInBasketTitle.contains(arrayList.get(i).getName()))
        {test.log(LogStatus.PASS, "the same product added"+arrayList.get(i).getName());}
        else
        {test.log(LogStatus.FAIL, "Test Failed product added"+arrayList.get(i).getName());}
        //second step check when I click in proced checkout btton then the site redirect destination to login page
        String titlepagelogin=loginPage.checkLogin(driver);
       if(titlepagelogin.contains("SE CONNECTER")||titlepagelogin.contains("LOG IN"))
       {test.log(LogStatus.PASS, "login page appears"+arrayList.get(i).getName());}
       else
       {test.log(LogStatus.FAIL, "Test Failed login page"+arrayList.get(i).getName());}
        driver.navigate().to("https://www.nespresso.com/fr/fr/");

    }



    }

    public static void main(String[] args) throws IOException{

        }
    @After
    public  void afterClass() {
        report.endTest(test);
        report.flush();
        //driver.close();
    }



    }



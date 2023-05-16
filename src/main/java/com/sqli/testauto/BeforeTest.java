package com.sqli.testauto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BeforeTest {
    static ExtentTest test;
    static ExtentReports report;
    WebDriver driver;
    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest test) {
        BeforeTest.test = test;
    }

    public static ExtentReports getReport() {
        return report;
    }

    public static void setReport(ExtentReports report) {
        BeforeTest.report = report;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public ArrayList<Catalogue> convertJsonToArrayList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode datafake = objectMapper.readTree(new File("src/webDriver/testDtata.json"));
        String json = datafake.toString();
        Gson gson = new Gson();
        Catalogue[] catalogueArray = gson.fromJson(json, Catalogue[].class);
        ArrayList<Catalogue> arrayList = new ArrayList<>(Arrays.asList(catalogueArray));
        return arrayList;
    }
    public void setUpReport() throws IOException {
        //report
        report = new ExtentReports(System.getProperty("user.dir") + "//NesspressoReportResults1.html");
        test = report.startTest("nespressoReportsDemo1");

    }
    public WebDriver setUpWebDriver(){
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
        //access to website
        driver.get("https://www.nespresso.com/fr/fr/");
        driver.manage().deleteAllCookies();
        return driver;
    }
    public  void afterClass() {
        report.endTest(test);
        report.flush();
        //driver.close();
    }


}

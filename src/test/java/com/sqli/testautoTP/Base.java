package com.sqli.testautoTP;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.io.IOException;


public class Base {
    private WebDriver driver;

    static ExtentTest test;
    static ExtentReports report;
    public String browserName;
    public static ExtentTest getTest() {
        return test;
    }

    public void setTest(ExtentTest test) {
        this.test = test;
    }

    public static ExtentReports getReport() {
        return report;
    }

    public  void setReport(ExtentReports report) {
        this.report = report;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public void setUpReport() throws IOException {
        //report
        report = new ExtentReports(System.getProperty("user.dir") + "com/sqli/testautoTP/testNespresso.html");
        test = report.startTest("nespressoReportsDemo1");

    }
    public WebDriver setUpWebDriver() {
        //setup firefox driver
        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.driver", "src/webDriver/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            // options.addPreference("dom.webdriver.enabled", false);
            options.setHeadless(false);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("dom.webnotifications.enabled", false);
            options.setProfile(firefoxProfile);
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("chrome")) {

            //Setup chrome driver
            System.setProperty("webdriver.chrome.driver", "src/webDriver/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--remote-allow-origins=*");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
            //options.addArguments("--disable-javascript");
            //options.addArguments("--incognito");
            //options.setPageLoadStrategy(PageLoadStrategy.NONE);
            driver = new ChromeDriver(options);

        }
        if (driver != null) {
            driver.manage().window().maximize();
            driver.get("https://ma.buynespresso.com/ma_fr/cafe.html");
            driver.manage().deleteAllCookies();
        }
        return driver;
    }
    public Base(WebDriver driver, String browserName,ExtentTest test, ExtentReports report) {
        this.driver = driver;
        this.browserName=browserName;
        this.test =test;
        this.report=report;

    }

    public void afterTestSetUp() {
        report.endTest(test);
        report.flush();
        driver.manage().deleteAllCookies();
        driver.close();

    }
}

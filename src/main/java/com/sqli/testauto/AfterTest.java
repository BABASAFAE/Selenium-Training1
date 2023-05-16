package com.sqli.testauto;

import org.openqa.selenium.WebDriver;

public class AfterTest extends BeforeTest{

    public AfterTest(WebDriver driver) {
        this.driver=driver;
    }

    public  void afterTestSetUp() {
        report.endTest(test);
        report.flush();
        driver.manage().deleteAllCookies();
        driver.close();

    }
}

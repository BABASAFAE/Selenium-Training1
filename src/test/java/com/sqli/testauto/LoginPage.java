package com.sqli.testauto;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage  {
    WebDriver driver;
    ExtentTest test;
    @FindBy(xpath = "//button[@id='ta-mini-basket__checkout']")
    WebElement buttonProceedTocheckout;
    @FindBy(xpath = "//div[@id='loginContainer']/h2[@class='heading']")
    WebElement title;
    public LoginPage(WebDriver driver,ExtentTest test) {
        this.driver=driver;
        this.test=test;
        PageFactory.initElements(driver, this);
    }
    public void proceedToCheckout(){
        //Fluent wait setup
        FluentWait waitfluente = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        //click to proced checkout button
        waitfluente.until(ExpectedConditions.elementToBeClickable(buttonProceedTocheckout));
        buttonProceedTocheckout.click();
    }
    //check when I click proced checkout button from basket then the site redirect destination  to login
    public String checkDisplayLoginPage( String productName) throws InterruptedException {

        String titleLoginPage= new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOf(title)).getText();

        //check the title of login
        try{
            if(titleLoginPage.contains("SE CONNECTER")||titleLoginPage.contains("LOG IN"))
            {test.log(LogStatus.PASS, "login page appears"+productName);}
            else
            {test.log(LogStatus.FAIL, "Test Failed login page"+productName);}

        }catch(Exception ex){}
         assertThat(titleLoginPage, anyOf(containsString("SE CONNECTER"), containsString("LOG IN")));
        return titleLoginPage;
    }


}

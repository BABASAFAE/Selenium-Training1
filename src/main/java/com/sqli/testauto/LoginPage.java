package com.sqli.testauto;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginPage extends HomePage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    //check when I click proced checkout button from basket then the site redirect destination  to login
    public String checkLogin(WebDriver driver, String productName, ExtentTest test) throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluente = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        Actions actions = new Actions(driver);
        //click to proced checkout button
        waitfluente.until(ExpectedConditions.elementToBeClickable(super.buttonProceedTocheckout));
        super.buttonProceedTocheckout.click();
       WebElement loginElement=(WebElement) waitfluente.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginContainer']/h2[@class='heading']")));
        //get title of header for the page
        actions.moveToElement(loginElement);
        String titleLoginPage= loginElement.getText();

        if(titleLoginPage.contains("SE CONNECTER")||titleLoginPage.contains("LOG IN"))
        {test.log(LogStatus.PASS, "login page appears"+productName);}
        else
        {test.log(LogStatus.FAIL, "Test Failed login page"+productName);}
        return titleLoginPage;
    }

}

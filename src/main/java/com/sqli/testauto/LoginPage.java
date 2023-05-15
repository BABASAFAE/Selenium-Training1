package com.sqli.testauto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginPage {
    //check when I click proced checkout button from basket then the site redirect destination  to login
    public String checkLogin(WebDriver driver) throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluente = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        Actions actions = new Actions(driver);
        //click to proced checkout button
        WebElement buttonPprocedCheckout= driver.findElement(By.xpath("//button[@id='ta-mini-basket__checkout']"));
        buttonPprocedCheckout.click();
        WebElement loginElement=(WebElement) waitfluente.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginContainer']/h2[@class='heading']")));
        //get title of header for the page
        actions.moveToElement(loginElement);
        String titleLoginPage= loginElement.getText();
        return titleLoginPage;
    }

}
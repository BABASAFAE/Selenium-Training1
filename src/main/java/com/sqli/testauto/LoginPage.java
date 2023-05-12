package com.sqli.testauto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private String title;
    private String email;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    //check when I click proced checkout button from basket then the site redirect destination  to login
    public String checkLogin(WebDriver driver) throws InterruptedException {
        driver.manage().deleteAllCookies();
        Actions actions = new Actions(driver);
      //click to proced checkout button

        WebElement buttonPprocedCheckout= driver.findElement(By.xpath("//button[@id='ta-mini-basket__checkout']"));
        actions.moveToElement(buttonPprocedCheckout);
        actions.click().build().perform();
        driver.navigate().refresh();
         driver.manage().deleteAllCookies();
        //decline cookie
        driver.findElement(By.xpath("//button[@id='_evidon-barrier-declinebutton']")).click();
        WebDriverWait waite = new WebDriverWait(driver, Duration.ofSeconds(30));
        waite.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginContainer']/h2[@class='heading']")));

        WebElement loginEelement=driver.findElement(By.xpath("//div[@id='loginContainer']/h2[@class='heading']"));

        //explicit wait login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginContainer']/h2[@class='heading']")));
        //get title of header for the page
        actions.moveToElement(loginEelement);
        String titleLoginPage= loginEelement.getText();
        return titleLoginPage;
    }

}

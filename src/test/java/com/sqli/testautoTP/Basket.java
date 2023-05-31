package com.sqli.testautoTP;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;

public class Basket {

    @FindBy(xpath = "//a[contains(@class,'showcart')]")
    WebElement basket;
    @FindBy(xpath = "//div[contains(@class,'minicart-items-wrapper')]//strong//a[contains(@href,'tokyo-vivalto-lungo')]")
    WebElement title;
    private WebDriver driver;
    static ExtentTest test;
    static ExtentReports report;
    FluentWait waitfluent;
    public Basket(WebDriver driver,ExtentTest test) {
        this.driver = driver;
        this.test =test;
        PageFactory.initElements(driver, this);

        waitfluent = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException .class);
    }

public void checkProduct(String productName){
    waitfluent.until(ExpectedConditions.elementToBeClickable(basket));
    basket.click();
    WebElement productInBasket = (WebElement) waitfluent.until(ExpectedConditions.elementToBeClickable(title));
    //click on  sub menu
    String titleProductInBasket = productInBasket.getText();
    try{
        if(titleProductInBasket.contains("TOKYO VIVALTO LUNGO"))
        {test.log(LogStatus.PASS, "product exist into basket"+productName);}
        else
        {test.log(LogStatus.FAIL, "product doesnt exist into basket"+productName);}

    }catch(Exception ex){}
    //Check product name
    assertThat(titleProductInBasket, containsStringIgnoringCase(productName));
}





}

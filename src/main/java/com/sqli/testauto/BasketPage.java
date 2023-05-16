package com.sqli.testauto;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class BasketPage extends HomePage{
    WebDriver driver;

    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    //check if the product is in the basket
    public String procedToCheckout(WebDriver driver,String productTitle,String productName,ExtentTest test) throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluentb = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        //open basket
        /*WebElement basket = (WebElement)  waitfluentb.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"ta-mini-basket__open\"]")));
        basket.click();*/
        waitfluentb.until(ExpectedConditions.elementToBeClickable(super.buttonOpenBasket));
        super.buttonOpenBasket.click();
        // Move to product in basket and get title
        WebElement  productInBasket=(WebElement)  waitfluentb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productTitle)));
        String titleProductInBasket= productInBasket.getText();
        //Check product name

        if(titleProductInBasket.contains(productName))
        {test.log(LogStatus.PASS, "the same product added"+productName);}
        else
        {test.log(LogStatus.FAIL, "Test Failed product added"+productName);}
        return titleProductInBasket;

    }
    public void Checkbasket(){

    }
}

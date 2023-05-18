package com.sqli.testauto;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import static org.openqa.selenium.By.xpath;

public class BasketPage{
    WebDriver driver;
    @FindBy(xpath = "//button[@id=\"ta-mini-basket__open\"]")
    WebElement buttonOpenBasket;

    public BasketPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //check if the product is in the basket
    public void procedToCheckout(WebDriver driver,String productTitle,String productName,ExtentTest test) throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluentb = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        //open basket
        waitfluentb.until(ExpectedConditions.elementToBeClickable(buttonOpenBasket));
        buttonOpenBasket.click();
        // Move to product in basket and get title
        WebElement productInBasket = (WebElement) waitfluentb.until(ExpectedConditions.elementToBeClickable((xpath("//td[contains(@headers,'" + productTitle + "')and (@class='MiniBasketItem__title')]"))));
        String titleProductInBasket = productInBasket.getText();
        //Check product name
        if (titleProductInBasket.contains(productName)) {
            test.log(LogStatus.PASS, "the same product added" + productName);
        } else {
            test.log(LogStatus.FAIL, "Test Failed product added" + productName);
        }


    }


}

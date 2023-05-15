package com.sqli.testauto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class BasketPage {

    //check if the product is in the basket
    public String procedToCheckout(WebDriver driver,String productTitle) throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluentb = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        //open basket
        WebElement basket = (WebElement)  waitfluentb.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"ta-mini-basket__open\"]")));
        basket.click();
        // Move to product in basket and get title
        WebElement  productInBasket=(WebElement)  waitfluentb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productTitle)));
        String titleProductInBasket= productInBasket.getText();
        return titleProductInBasket;

    }

}

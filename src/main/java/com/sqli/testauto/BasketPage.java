package com.sqli.testauto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BasketPage {

    //check if the product is in the basket
    public String procedToCheckout(WebDriver driver) throws InterruptedException {
        driver.manage().deleteAllCookies();
        Actions actions = new Actions(driver);
        WebElement basket =driver.findElement(By.xpath("//button[@id=\"ta-mini-basket__open\"]"));//button[@id="ta-mini-basket__open"]

        //Hovering on basket
        actions.moveToElement(basket);


        actions.click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='MiniBasketItem']/td[@headers='capsules_original jamaica-blue-mountain-capsule-cafe-7726-50' and @class='MiniBasketItem__title']")));

        WebElement  productInBasket =driver.findElement(By.xpath("//tr[@class='MiniBasketItem']/td[@headers='capsules_original jamaica-blue-mountain-capsule-cafe-7726-50' and @class='MiniBasketItem__title']"));


        // Move to product in basket and get title
        actions.moveToElement(productInBasket);
        String titleProductInBasket= productInBasket.getText();

        return titleProductInBasket;

    }

}

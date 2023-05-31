package com.sqli.testautoTP;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class OriginalNespressoPage {
    private WebDriver driver;
    Actions actions;
    FluentWait waitfluent;

    public OriginalNespressoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitfluent = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    @FindBy(xpath = "//div[contains(@class,'category-buttons')]//a[contains(@href,'cafe/original.html')]")
    WebElement original;
    @FindBy(xpath = "//div[contains(@data-url,'tokyo-vivalto-lungo')]//button[contains(@class,'action tocart primary list')]")
    WebElement productButton;
    @FindBy(xpath = "//div[contains(@data-url,'tokyo-vivalto-lungo')]//div[contains(@class,'qty-box__overlay-wrapper')]//li[contains(@class,'qty-item')]/span[contains(@data-qtyitem,'10')]")
    WebElement quantity;
    @FindBy(xpath = "//button[contains(@id,'_evidon-accept-button')]")
    WebElement declinebutton;

    public void lookingForProduct(){


        waitfluent.until(ExpectedConditions.elementToBeClickable(original));
        original.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Scroll down the page by pixel offset
        jsExecutor.executeScript("window.scrollBy(0, 500);");
        waitfluent.until(ExpectedConditions.elementToBeClickable(declinebutton));
        declinebutton.click();

    }
    public void addProduct() throws InterruptedException {
        waitfluent.until(ExpectedConditions.elementToBeClickable(productButton));
        productButton.click();
        waitfluent.until(ExpectedConditions.elementToBeClickable(quantity));
        quantity.click();
        Thread.sleep(3000);
    }
}


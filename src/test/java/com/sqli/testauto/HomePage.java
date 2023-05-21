package com.sqli.testauto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public class HomePage {

WebDriver driver;
    @FindBy(xpath = "//button[@id='_evidon-barrier-declinebutton']")
    WebElement buttonDeclinePopup;
    @FindBy(xpath = "//button[contains(@id,'ta-product-details__add-to-bag-button')]")
    WebElement buttonAddToBag;
    @FindBy(xpath = " //button[@id='ta-quantity-selector__predefined-1']")
    WebElement buttonChooseQuantity;
    @FindBy(xpath = "//a[contains(@class,'HeaderNavigationBarDropdown__medium-link')]")
    WebElement submenu;
    //Fluent wait setup

    public void ChooseArticle(String type,String articledata) throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluent = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        //First step go  to order coffee
        System.out.println("title test "+ driver.getTitle());
        Actions actions = new Actions(driver);
        try{

            waitfluent.until(ExpectedConditions.elementToBeClickable(buttonDeclinePopup));
            buttonDeclinePopup.click();

        }catch(Exception ex) {
            System.out.println("decline popup not visible"); }
        //hover on the menu
        WebElement menu =  (WebElement)waitfluent.until(ExpectedConditions.elementToBeClickable((xpath("  //ul[contains(@class,'HeaderNavigationBar')]/li[contains(@class,'HeaderNavigationBarItem')]/a[contains(@href,'/order/"+type+"/')]"))));
        //click on  sub menu
        new Actions(driver).moveToElement(menu).perform();
        //waitfluent.until(ExpectedConditions.visibilityOf(submenu));
       new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOf(submenu));
       actions.moveToElement(submenu).click().perform();
        //go and click to the first article
        WebElement article = (WebElement) waitfluent.until(ExpectedConditions.elementToBeClickable((xpath(" //article[contains(@data-product-position,'1')]//a[contains(@href,'/"+articledata+"')]"))));
        article.click();

       //driver.navigate().refresh();


    }


    public void AddToBasket() throws InterruptedException {
        //Fluent wait setup
        FluentWait waitfluent = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        waitfluent.until(ExpectedConditions.elementToBeClickable(buttonAddToBag));
        buttonAddToBag.click();
        //go and select quantite 10 to added into basket
        waitfluent.until(ExpectedConditions.elementToBeClickable(buttonChooseQuantity));
        buttonChooseQuantity.click();
        Thread.sleep(3000);

    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

package com.sqli.testauto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;


import static org.openqa.selenium.By.*;

public class OrderPageCf1 {


//factory page example
    @FindBy(xpath = "//button[@id='_evidon-barrier-declinebutton']")
    WebElement buttonDeclinePopup;

    public void ChooseArticle(WebDriver driver,String type,String articledata){
        //Fluent wait setup
        FluentWait waitfluent = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        //First step go  to order coffee
        System.out.println("title test "+ driver.getTitle());

        try{
            WebElement buttondeclin = (WebElement) waitfluent.until(ExpectedConditions.elementToBeClickable(xpath("//button[@id='_evidon-barrier-declinebutton']")));
            buttondeclin.click();
        }catch(Exception ex) {
            System.out.println("decline popup not visible"); }

        WebElement menu =  (WebElement)waitfluent.until(ExpectedConditions.elementToBeClickable((xpath("//li[contains(@class,'HeaderNavigationBarItem')]/a[contains(@href,'/order/"+type+"/')]"))));
        //click on  sub menu
        menu.click();
        //go and click to the first article
        WebElement article = (WebElement) waitfluent.until(ExpectedConditions.elementToBeClickable((xpath(articledata))));
        article.click();


    }



    public void AddToBasket(WebDriver driver) throws InterruptedException {
        //Instantiating Actions class
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //  buttonAddToBasket.click();
        System.out.println("pannier");
        Thread.sleep(3000);
        //explicit wait button will be cliquable  //go and click in button add to basket
        WebElement buttonAdd= wait.until(ExpectedConditions.elementToBeClickable(xpath("//button[contains(@id,'ta-product-details__add-to-bag-button')]")));
        buttonAdd.click();
        //go and select quantite 10 to added into basket
        WebElement selectionnerQuantite10= wait.until(ExpectedConditions.elementToBeClickable(xpath(" //button[@id='ta-quantity-selector__predefined-1']")));
        selectionnerQuantite10.click();
        Thread.sleep(3000);





    }
}

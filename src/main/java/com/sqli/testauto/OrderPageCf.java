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

public class OrderPageCf {


public void ChooseArticle(WebDriver driver){
    //First step go  to order coffee
    // Locating the Main Menu (Parent element)
    System.out.println("title test "+ driver.getTitle());

    WebElement mainMenu = driver.findElement(By.xpath("//ul[@class='HeaderNavigationBar__menu']"));
    //Instantiating Actions class
    Actions actions = new Actions(driver);
    //Hovering on main menu
    actions.moveToElement(mainMenu);
    // Locating the element from Sub Menu order coffee
    WebElement subMenu =  driver.findElement(By.xpath("//li[contains(@class,'HeaderNavigationBarItem')]/a[contains(@href,'capsules/original')]"));
    //To mouseover on sub menu
    actions.moveToElement(subMenu);
    //second step click in order coffee
    actions.click().build().perform();
    //decline cookie
    driver.findElement(By.xpath("//button[@id='_evidon-barrier-declinebutton']")).click();
    //implicit wait
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    //go and click to the first article in gamme original Jamaica blue Mountain
    WebElement article = driver.findElement(By.xpath(" //article[contains(@class,'ProductCard') and contains(@data-product-item-id,'7726.50')and contains(@data-product-section,\"Special Reserve - Edition Exclusive\")]"));
    actions.moveToElement(article);
    driver.manage().deleteAllCookies();
    actions.click().build().perform();
    //delete all cookies
    driver.manage().deleteAllCookies();
}
    public void AddToBasket(WebDriver driver) throws InterruptedException {
        //Instantiating Actions class
        Actions actions = new Actions(driver);
        //decline cookie
        WebDriverWait waiteee = new WebDriverWait(driver, Duration.ofSeconds(30));
        waiteee.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='_evidon-barrier-declinebutton']")));

        driver.findElement(By.xpath("//button[@id='_evidon-barrier-declinebutton']")).click();
        //explicit wait button will be cliquable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'ta-product-details__add-to-bag-button')]")));
        driver.manage().deleteAllCookies();
        //go and click in button add to basket
        WebElement buttonAdd = driver.findElement(By.xpath("//button[contains(@id,'ta-product-details__add-to-bag-button')]"));
        actions.moveToElement(buttonAdd);
        actions.click().build().perform();
        //implicit wait for appearing selection box
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //go and select quantite 10 to added into basket
        WebElement selectionnerQuantite10=driver.findElement(By.xpath(" //button[@id='ta-quantity-selector__predefined-1']"));
        actions.moveToElement(selectionnerQuantite10);
        actions.click().build().perform();
        Thread.sleep(3000);


    }
}

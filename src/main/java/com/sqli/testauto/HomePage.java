package com.sqli.testauto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    //Factory Page
    @FindBy(xpath = "//button[@id='_evidon-barrier-declinebutton']")
    WebElement buttonDeclinePopup;
    @FindBy(xpath = "//button[contains(@id,'ta-product-details__add-to-bag-button')]")
    WebElement buttonAddToBag;
    @FindBy(xpath = " //button[@id='ta-quantity-selector__predefined-1']")
    WebElement buttonChooseQuantity;
    @FindBy(xpath = "//button[@id=\"ta-mini-basket__open\"]")
    WebElement buttonOpenBasket;
    @FindBy(xpath = "//button[@id='ta-mini-basket__checkout']")
    WebElement buttonProceedTocheckout;
    @FindBy(xpath = "//div[@id='loginContainer']/h2[@class='heading']")
    WebElement TitlePopupLogin;

    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }



}

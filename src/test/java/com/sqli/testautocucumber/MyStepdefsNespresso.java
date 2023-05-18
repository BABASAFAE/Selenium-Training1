package com.sqli.testautocucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;
import static org.openqa.selenium.By.xpath;


public class MyStepdefsNespresso {
    //cmd : mvn test -Dtest=CucumberRunner "-Dcucumber.options=--tags @Ready"
    private WebDriver driver;
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
    WebElement title;
    Actions actions;
    FluentWait waitfluent;
    //Fluent wait setup
    @Before
            public void setup(){
        System.setProperty("webdriver.chrome.driver","src/webDriver/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
         actions = new Actions(driver);
         waitfluent = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    @Given("^the user is on the homepage$")
    public void the_user_is_on_the_homepage() throws Throwable {

        driver.get("https://www.nespresso.com/fr/fr/");
    }
    @When("the user hovers over the {} menu")
    public void theUserHoversOverTheMenu(String type) {


        //First step go  to order coffee
        System.out.println("title test "+ driver.getTitle());

        try{

            waitfluent.until(ExpectedConditions.elementToBeClickable(buttonDeclinePopup));
            buttonDeclinePopup.click();

        }catch(Exception ex) {
            System.out.println("decline popup not visible"); }

        WebElement menu =  (WebElement)waitfluent.until(ExpectedConditions.elementToBeClickable((xpath("  //ul[contains(@class,'HeaderNavigationBar')]/li[contains(@class,'HeaderNavigationBarItem')]/a[contains(@href,'/order/"+type+"/')]"))));
        //click on  sub menu
        new Actions(driver).moveToElement(menu).perform();
    }
    @And("the user clicks on the submenu")
    public void theUserClicksOnTheSubmenu() {

        waitfluent.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'AccessibleLink HeaderNavigationBarDropdown__medium-link')]")));
        WebElement submenu = driver.findElement(By.xpath("//a[contains(@class,'AccessibleLink HeaderNavigationBarDropdown__medium-link')]"));
        actions.moveToElement(submenu).click().perform();

    }
    @And("^the user clicks on the article (.+)$")
    public void the_user_clicks_on_the_article(String article) throws Throwable {
//go and click to the first article
        WebElement articleProduct = (WebElement) waitfluent.until(ExpectedConditions.elementToBeClickable((xpath(" //article[contains(@data-product-position,'1')]//a[contains(@href,'/"+article+"')]"))));
        articleProduct.click();
        driver.navigate().refresh();
    }
    @And("The user clicks on the Add to Basket button")
    public void theUserClicksOnTheAddToBasketButton() throws InterruptedException {

        System.out.println("pannier");
        Thread.sleep(3000);
        waitfluent.until(ExpectedConditions.elementToBeClickable(buttonAddToBag));
        buttonAddToBag.click();
        //go and select quantite 10 to added into basket


    }

    @And("^the user selects the quantity$")
    public void the_user_selects_the_quantity() throws Throwable {
        waitfluent.until(ExpectedConditions.elementToBeClickable(buttonChooseQuantity));
        buttonChooseQuantity.click();
        Thread.sleep(3000);
    }

@And("^The user clicks on the Your Basket button$")
public void the_user_clicks_on_the_your_basket_button() throws Throwable {
    waitfluent.until(ExpectedConditions.elementToBeClickable(buttonOpenBasket));
    buttonOpenBasket.click();
    Thread.sleep(3000);

}
    @Then("the product should be listed in the basket {} and product name {}")
    public void theProductShouldBeListedInTheBasketAndProductName(String productTitle, String productName) throws Throwable  {
        // Move to product in basket and get title
        WebElement productInBasket = (WebElement) waitfluent.until(ExpectedConditions.elementToBeClickable((xpath("//td[contains(@headers,'" + productTitle + "')and (@class='MiniBasketItem__title')]"))));
        //click on  sub menu
        String titleProductInBasket = productInBasket.getText();
        //Check product name
        if(titleProductInBasket.contains(productName))
        {System.out.println( "the same product added"+productName);}
        else
        {System.out.println("Test Failed product added"+productName);}


    }
    @And("^The user clicks on Proceed to Checkout button$")
    public void the_user_clicks_on_proceed_to_checkout_button() throws Throwable {
        //click to proced checkout button
        waitfluent.until(ExpectedConditions.elementToBeClickable(buttonProceedTocheckout));
        buttonProceedTocheckout.click();
    }


    @Then("^The login page displays (.+)$")
    public void the_login_page_displays(String productName) throws Throwable {
        //check the title of login
        String titleLoginPage= new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.visibilityOf(title)).getText();
        System.out.println("title login page "+titleLoginPage);
        if(titleLoginPage.contains("SE CONNECTER")||titleLoginPage.contains("LOG IN"))
        {System.out.println( "login page appears"+productName);}
        else
        {System.out.println( "Test Failed login page"+productName);}
    }



}

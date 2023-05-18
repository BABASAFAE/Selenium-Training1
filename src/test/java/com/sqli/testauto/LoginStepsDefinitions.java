package com.sqli.testauto;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;


//@RunWith(Cucumber.class)
public class LoginStepsDefinitions {
    //cmd : mvn test -Dtest=CucumberRunner "-Dcucumber.options=--tags @Ready"
    private WebDriver driver;
    @FindBy(xpath = "//button[@id='_evidon-barrier-declinebutton']")
    WebElement buttonDeclinePopup;
    @FindBy(xpath = "//button[@id='ta-login-dropdown--not-logged']")
    WebElement buttonConnectezVous;

    @FindBy(xpath = "//button[@id='ta-login-form__submit']")
    WebElement buttonIdentifier;



    @Given("the user is on the homepage")
    public void user_is_on_homepage() {
        System.setProperty("webdriver.chrome.driver","src/webDriver/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.nespresso.com/fr/fr/");
    }

    @When("^user fill username (.+) and password (.+)$")
    public void user_fill_username_and_password(String username, String password) throws Throwable {


        //Fluent wait setup
        FluentWait waitfluente = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
        System.out.println("test 1");
        waitfluente.until(ExpectedConditions.elementToBeClickable(buttonDeclinePopup));
        buttonDeclinePopup.click();
        System.out.println("test 2");

        waitfluente.until(ExpectedConditions.elementToBeClickable(buttonConnectezVous));
        buttonConnectezVous.click();
        System.out.println("test 3");
        Actions actions = new Actions(driver);
        //email
        waitfluente.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
        WebElement emailInput=(WebElement) waitfluente.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        actions.moveToElement(emailInput);
        emailInput.sendKeys(username);
        System.out.println("test 4"+username);
        //password
        waitfluente.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
        WebElement paswordInput=(WebElement) waitfluente.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        actions.moveToElement(paswordInput);
        paswordInput.sendKeys(password);
        System.out.println("test 5"+password);

        waitfluente.until(ExpectedConditions.elementToBeClickable(buttonIdentifier));
        buttonIdentifier.click();
        System.out.println("test 6");
    }

    @Then("^Error message is displayed$")
    public void error_message_is_displayed() throws Throwable {
        //Fluent wait setup
        FluentWait waitfluente = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);

        WebElement redMessage=(WebElement) waitfluente.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='email-error-message']")));
        //get title of header for the page
        // actions.moveToElement(loginElement);
        String redMessageText= redMessage.getText();
        System.out.println("test 8");
        //check the title of login
        if(redMessageText.contains("Votre identifiant et/ou mot de passe n'est pas valide. Veuillez réessayer votre saisie"))
        {System.out.println( "password or email incorrect");}
        else
        {System.out.println("password and email correct");}

    }



}

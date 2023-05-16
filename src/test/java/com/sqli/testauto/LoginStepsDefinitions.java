package com.sqli.testauto;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class LoginStepsDefinitions {

    @Given("^user is on homepage$")
    public void user_is_on_homepage() throws Throwable {
        throw new PendingException();
    }

    @When("^user fill username (.+) and password (.+)$")
    public void user_fill_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Then("^Error message is displayed$")
    public void error_message_is_displayed() throws Throwable {
        throw new PendingException();
    }

    @And("^user navigates to Login Page$")
    public void user_navigates_to_login_page() throws Throwable {
        throw new PendingException();
    }
}

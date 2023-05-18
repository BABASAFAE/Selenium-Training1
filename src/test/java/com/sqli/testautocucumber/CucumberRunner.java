package com.sqli.testautocucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features/AddProductToBasket.feature", glue = "com.sqli.testautocucumber", plugin = {"pretty", "html:target/htmlreports.html", "json:target/htmlreports.json"})
public class CucumberRunner {

}
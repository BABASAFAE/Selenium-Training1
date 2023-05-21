package com.sqli.cucumbergherking;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features/basket/", glue = "com.sqli.cucumbergherking", plugin = {"pretty", "html:target/htmlreports.html", "json:target/htmlreports.json"})
public class CucumberRunnerTest {

}
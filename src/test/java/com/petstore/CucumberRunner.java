package com.petstore;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
//        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
        plugin = {"pretty", "html:target/cucumber-report"},

        features = "classpath:features",
        glue = {"classpath:com/petstore/steps"}
)
public class CucumberRunner {
}

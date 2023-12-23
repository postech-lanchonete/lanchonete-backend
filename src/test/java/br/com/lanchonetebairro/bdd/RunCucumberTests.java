package br.com.lanchonetebairro.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = "src/test/resources/features",
        glue = {"br.com.lanchonetebairro.bdd"})
public class RunCucumberTests {
}

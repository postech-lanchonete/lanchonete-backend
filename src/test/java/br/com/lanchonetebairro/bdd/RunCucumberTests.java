package br.com.lanchonetebairro.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"br.com.lanchonetebairro.bdd"})
public class RunCucumberTests {
}

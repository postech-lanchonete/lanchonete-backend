package br.com.lanchonetebairro.bdd;

import br.com.lanchonetebairro.interfaceadapters.repositories.ProdutoRepository;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/produto", glue = "br.com.lanchonetebairro.bdd.steps.produto")
public class CucumberSpringConfiguration {
}

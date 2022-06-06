package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)														////tags= "@DeletePlace"
@CucumberOptions(features="src/test/java/features", glue= {"stepDefinitions"}
, plugin= "json:target/cucumber.json")
public class TestRunner {
	
}

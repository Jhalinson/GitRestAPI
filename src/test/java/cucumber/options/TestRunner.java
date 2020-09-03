package cucumber.options;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//t verify-Dcucumber.options="--tags@"$tag""
@CucumberOptions(features = "src/test/java/features", 
				 plugin = "json:target/jsonReports/cucumber-report.json", 
				 glue = {"stepDefinition" })
public class TestRunner {
	// , tags = {"@DeletePlace"})

}

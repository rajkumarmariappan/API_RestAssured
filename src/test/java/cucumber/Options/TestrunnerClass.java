package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		 			monochrome = true,
		 			plugin = { "pretty","html:target/cucumber-pretty.html", "json:target/cucumber.json" },
		 			features = "src/test/java/features", 
		 			glue = {"stepDef"},
		 			tags ="@Regression",
		 			dryRun = false
		 		)
public class TestrunnerClass {

}

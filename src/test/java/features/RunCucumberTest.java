package features;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber; //Junit test runner
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        glue = "features",
        strict = true,
        plugin = {"pretty",
                "html:target/cucumberReports/mobile/html",
                "json:target/cucumberReports/mobile/json"
        })
public class RunCucumberTest {

}

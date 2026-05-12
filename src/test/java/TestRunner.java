import drivers.DriverFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {


    @AfterSuite
    public void tearDown() {
        DriverFactory.fecharDriver();
    }
}

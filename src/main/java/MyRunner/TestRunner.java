package MyRunner;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
//import cucumber.api.CucumberOptions;
//import cucumber.api.testng.CucumberFeatureWrapper;
//import cucumber.api.testng.TestNGCucumberRunner;



@CucumberOptions(
        features = {"src/main/java/Features"},
        glue = {"stepDefinitions"},
     //   tags = {"~@Ignore"},
        plugin = {"json:target/cucumber-reports/CucumberTestReport.json",
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty1",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "rerun:target/cucumber-reports/rerun.txt"})

public class TestRunner {
    private TestNGCucumberRunner testNGCucumberRunner;
 
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        // the 'featureWrapper' parameter solely exists to display the feature
        // file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }
	
    @DataProvider
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }
    
	/*
	 * @Test(groups = "cucumber", description = "Runs Cucumber Feature",
	 * dataProvider = "features") public void feature(FeatureWrapper
	 * cucumberFeature) { testNGCucumberRunner.runCucumber(cucumberFeature); }
	 */
	  
	/*
	 * @DataProvider public Object[][] features() { return
	 * testNGCucumberRunner.provideFeatures(); }
	 */
	 
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
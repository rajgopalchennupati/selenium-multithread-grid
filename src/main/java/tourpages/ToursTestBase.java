package tourpages;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import drivercreation.CreateDriverOld;
import drivercreation.Global_VARS;

public class ToursTestBase {
	
	
	@BeforeTest
    public void beforeTest() throws Exception {	
		
		//DriverProvider.getInstance().getDriver().get("https://www.marriott.com/");
		//DriverProvider.getInstance().getDriver().manage().window().maximize();
		//DriverProvider.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		CreateDriverOld.getInstance().setDriver(Global_VARS.DEF_BROWSER,  Global_VARS.DEF_PLATFORM,   Global_VARS.DEF_ENVIRONMENT);
		WebDriver driver = CreateDriverOld.getInstance().getDriver();
		
	}
	
	@AfterTest
	public void afterTest() {

		//CreateDriver.getInstance().closeDriver();		 
	}
	
	

}

package marriotpages;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import drivercreation.CreateDriver;
import drivercreation.Global_VARS;
import extentreports.CreateLink;
import extentreports.Reporting;
import io.restassured.path.json.JsonPath;
import marriotpages.HomePage;


public class BaseTest extends Reporting {
	private static final int FILE = 0;
	public HomePage home;
	public ConcurrentHashMap<String, String> location;
	public ConcurrentHashMap<String, String> username;
	
	@BeforeSuite
	public void startReporting() {
		initializeReport();
	}
	
	
	@AfterSuite
	public void endReporting() {
		generateReport();
	}
	
	@BeforeMethod
     public void beforeTest(Method result) throws Exception {	
		
		ExtentTest extentTestLogger = report.createTest(result.getName());
		
		CreateDriver.getInstance().setDriver(Global_VARS.DEF_BROWSER, Global_VARS.DEF_ENVIRONMENT, Global_VARS.DEF_PLATFORM,  extentTestLogger);
		
		CreateDriver.getInstance().getDriver().get("https://www.marriott.com/");
		CreateDriver.getInstance().getDriver().manage().window().maximize();
		CreateDriver.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		home = new HomePage();
		
		
	}
	
	@AfterMethod
	public void afterTest(ITestResult res) {
		String status = CreateDriver.getInstance().getExtentTestLogger().getStatus().toString();
		System.out.println(status);
		if(status.equals("fail")) {
			captureScreenshot();
		}
		CreateDriver.getInstance().closeDriver();		 
	}
	
	

	@BeforeClass
	public void readTestData() throws ClassNotFoundException {
		String path = System.getProperty("user.dir")+"/testdata/testdata.json";		
		JsonPath json = new JsonPath(new File(path));
		location = (ConcurrentHashMap<String, String>) json.getObject("Location", Class.forName("java.util.concurrent.ConcurrentHashMap"));
		username = (ConcurrentHashMap<String, String>) json.getObject("usernames", Class.forName("java.util.concurrent.ConcurrentHashMap"));	
		
				
	}
	
	
	private synchronized void captureScreenshot() {
		
		String spath = System.getProperty("user.dir")+"/result/screeshot.jpg";
		File sourceFile = ((TakesScreenshot)CreateDriver.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(sourceFile,new File(spath));
			CreateLink mark = new CreateLink(spath, "Screenshot");
			CreateDriver.getInstance().getExtentTestLogger().log(Status.FAIL, mark);
			
		} catch (IOException e) {
			CreateDriver.getInstance().getExtentTestLogger().fail("Unable to take screenshot");
		}
		
	}
	
	
}

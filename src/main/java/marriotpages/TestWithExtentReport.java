package marriotpages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import drivercreation.CreateDriver;
import drivercreation.CreateDriver;
import drivercreation.Global_VARS;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestWithExtentReport extends Reporting {
	
	
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
		
		CreateDriver.getInstance().getDriver().get("https://www.bhphotovideo.com/");
		CreateDriver.getInstance().getDriver().manage().window().maximize();
		CreateDriver.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		//CreateDriverOld.testLogger.set(testLogger); // assing logger object to particular thread
		
	}
	
	
	@Test
	public void testOne() throws InterruptedException {
		System.out.println(Thread.currentThread().getId());
		CreateDriver.getInstance().getDriver().findElement(By.id("top-search-input")).sendKeys("pen drive");
		CreateDriver.getInstance().getExtentTestLogger().info("Searching for pen driver");
		
		CreateDriver.getInstance().getDriver().findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
		CreateDriver.getInstance().getExtentTestLogger().info("clicked on search button");
		
		CreateDriver.getInstance().getDriver().findElement(By.linkText("SanDisk 128GB Ultra Flair USB 3.0 Flash Drive")).click();
		CreateDriver.getInstance().getExtentTestLogger().info("clicked on the first link");
		
		Thread.sleep(2000);
	}
	
	@Test
	public void testTwo() throws InterruptedException {
		System.out.println(Thread.currentThread().getId());
		CreateDriver.getInstance().getDriver().findElement(By.id("top-search-input")).sendKeys("dell laptop");
		CreateDriver.getInstance().getExtentTestLogger().info("Searching for dell laptop");
		
		CreateDriver.getInstance().getDriver().findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
		CreateDriver.getInstance().getExtentTestLogger().info("clicking on search button");
		
		CreateDriver.getInstance().getDriver().findElement(By.linkText("Dell 15.6\" Latitude 3500 Laptop")).click();
		CreateDriver.getInstance().getExtentTestLogger().info("clicking on first link");
		
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void afterTest() {
		CreateDriver.getInstance().closeDriver();;
			
		
	}
}





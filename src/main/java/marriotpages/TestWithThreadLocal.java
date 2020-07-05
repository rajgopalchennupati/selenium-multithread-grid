package marriotpages;


import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import drivercreation.CreateDriver;
import drivercreation.CreateDriverOld;
import drivercreation.Global_VARS;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestWithThreadLocal {
	
	public static WebDriver driver;
	public static String chromePath = System.getProperty("user.dir")+"/resources/chromedriver.exe";
	
	
	
	@BeforeMethod
	public void beforeTest(Method result) throws Exception {	
	
   
		
		CreateDriverOld.getInstance().setDriver(Global_VARS.DEF_BROWSER, Global_VARS.DEF_ENVIRONMENT, Global_VARS.DEF_PLATFORM);
		
		CreateDriverOld.getInstance().getDriver().get("https://www.bhphotovideo.com/");
		CreateDriverOld.getInstance().getDriver().manage().window().maximize();
		CreateDriverOld.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	
	
	@Test
	public void testOne() throws Exception {
		System.out.println(Thread.currentThread().getId());
		CreateDriverOld.getInstance().getDriver().findElement(By.id("top-search-input")).sendKeys("pen drive");
		CreateDriverOld.getInstance().getDriver().findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
		CreateDriverOld.getInstance().getDriver().findElement(By.linkText("SanDisk 128GB Ultra Flair USB 3.0 Flash Drive")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void testTwo() throws Exception {
		System.out.println(Thread.currentThread().getId());
		CreateDriverOld.getInstance().getDriver().findElement(By.id("top-search-input")).sendKeys("dell laptop");
		CreateDriverOld.getInstance().getDriver().findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
		CreateDriverOld.getInstance().getDriver().findElement(By.linkText("Dell 15.6\" Latitude 3500 Laptop")).click();
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void afterTest() {
		CreateDriverOld.getInstance().closeDriver();
		
	}
}



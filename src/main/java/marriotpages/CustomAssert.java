package marriotpages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import drivercreation.CreateDriver;

public class CustomAssert {
	
	
	public Boolean pageTitle(String expectedTitle) {
		String title = null;
		try {
			 title = CreateDriver.getInstance().getDriver().getTitle();
			assertThat(title, containsString(expectedTitle));
			CreateDriver.getInstance().getExtentTestLogger().pass("Page Title Match : "+ title);
			return true;
		} catch(AssertionError e) {
			CreateDriver.getInstance().getExtentTestLogger().fail("Page Title Mismatch : "+ title);
			return false;
		} catch (Exception e1) {
			throw new FailTestException(e1);
		}
		
		
	}
	
	
	public static  Boolean textMatch(String actualText, String expectedText, String elementName) {
		try {
			assertThat(actualText,equalTo(expectedText));
			CreateDriver.getInstance().getExtentTestLogger().pass(MarkupHelper.createLabel("Text Match Succesful : "+ actualText + expectedText + elementName,ExtentColor.GREEN));
			return true;
		} catch (AssertionError e) {
			CreateDriver.getInstance().getExtentTestLogger().fail(MarkupHelper.createLabel("Text Mismatch : "+ actualText + expectedText + elementName,  ExtentColor.RED));
			return false;
		}
	}
	
	
	public Boolean isElementExists(By element, String elementName) {
		List<WebElement> elements = CreateDriver.getInstance().getDriver().findElements(element);
		if(elements.size()==0) {
			CreateDriver.getInstance().getExtentTestLogger().fail("Element does not exists :"+ elementName);
			return false;
		}else {
			CreateDriver.getInstance().getExtentTestLogger().pass("Element exists : "+ elementName);
			return true;
		}
		
	}
	
	
	

}

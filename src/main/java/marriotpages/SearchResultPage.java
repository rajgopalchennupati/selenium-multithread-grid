package marriotpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


import drivercreation.CreateDriver;


public class SearchResultPage extends BasePage {
	public SearchResultPage() {
		PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
		//ExtentTest node = CreateDriver.getInstance().testLogger.get().createNode(this.getClass().getSimpleName());
		//CreateDriver.getInstance().nodeLog.set(node);
	}
	
	/******************* WebElement Declaration ***********************************/
	
	@FindBy(xpath="//a[@class='t-font-m t-line-height-l t-control-link l-display-inline-block t-dotdotdot']") 
				private WebElement destination;
	@FindBy(xpath="//span[contains(text(),'W London')]") private WebElement prop;
	@FindBy(xpath="//div[contains(@class,'l-margin-top-half l-padding-bottom-quarter')]//div[contains(@class,'')][contains(text(),'View Rates')]")
		private WebElement viewRates;
	
	
	
	public SearchResultPage verifyDestination(String expectedText) {
		String destinationText = op.getText(destination, "Destination Label");
		Boolean isDestinationMatch = check.textMatch(destinationText, expectedText, "verify Destination Label text");
		if(!isDestinationMatch) {
			throw new FailTestException("Mismatch in the destination location provided in homePage and Search Result Page");
		}
		return this;
		
	}
	
	public ChooseRoomPage Get_Rates() {
		op.clickThis(prop, "hotel button");
		CreateDriver.getInstance().getDriver().switchTo().activeElement();
		op.clickThis(viewRates, "view rates button");
		return new ChooseRoomPage();
	}
}


package marriotpages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import drivercreation.CreateDriver;


public class HomePage extends BasePage {

	//private WebDriver driver;
	public HomePage() throws Exception {
		//this.driver = driver;
		PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
				//ExtentTest node = CreateDriver.getInstance().testLogger.get().createNode(this.getClass().getSimpleName());
				//CreateDriver.getInstance().nodeLog.set(node);
		
	}
	
/******************* WebElement Declaration ***********************************/
	
@FindBy(name="destinationAddress.destination") private WebElement searchTextBox;
//@FindBy(xpath ="//div[@class='l-xs-col-4 l-xl-col-4 l-xl-last-col l-hsearch-find l-find-top js-hform-fields']//button[@class='analytics-click js-is-roomkey-enabled m-button m-button-primary'][contains(text(),'Find Hotels')] ")
@FindBy(xpath ="//*[@id=\"find-a-hotel-homePage-form\"]/div[2]/div[6]/button")
private WebElement findHotelsBtn;



@FindBy(xpath ="//span[contains(text(),'Sign In or Join')]") WebElement signIn;

@FindBy(id="user-id") private WebElement username;
@FindBy(id="password") private WebElement password;
@FindBy(name="submitButton") private WebElement signInBtn;

By myprofile = By.xpath("//a[@class='t-control-link m-profile-link-auth l-padding-half l-display-inline-block']");

	
/********************** WebElement Operations ***********************************/

public HomePage enterDestination(String destination) {	
	op.writeText(searchTextBox, destination, "Destination Search Text Box");
	
	return this;
}
	

public SearchResultPage findHotels() {
	op.clickThis(findHotelsBtn, "Find Hotels Button");
	return new SearchResultPage();
}
	
public void signIn(String usernametxt, String passwordtxt) {
	op.clickThis(signIn, "Signing In from HomePage");
	op.writeText(username, usernametxt, "username text box");
	op.writeText(password, passwordtxt, "password text box");
	op.clickThis(signInBtn, "Sign in Button");
	Boolean isExist = check.isElementExists(myprofile, "My profile element");
	if(!isExist) {
		throw new FailTestException("Login Failed");
	}else {
		CreateDriver.getInstance().getExtentTestLogger().info(MarkupHelper.createLabel("Login Successfull", ExtentColor.GREEN));	
	}
}
	
	
	
	
	
}

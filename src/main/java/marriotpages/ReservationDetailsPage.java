package marriotpages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


import drivercreation.CreateDriver;


public class ReservationDetailsPage extends BasePage {

	public ReservationDetailsPage() {
		PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
		//ExtentTest node = CreateDriver.getInstance().testLogger.get().createNode(this.getClass().getSimpleName());
		//CreateDriver.getInstance().nodeLog.set(node);
	}
	
/******************* WebElement Declaration ***********************************/
	@FindBy(xpath="//button[@class='analytics-click is-review-details-submit m-button m-button-primary l-padding-top-three-quarters l-padding-bottom-three-quarters js-submit-btn-res']")
			private WebElement continueBtn;
	
	@FindBy(linkText = "CONTINUE AS GUEST") private WebElement continueAsGuestlink;
	
	
	public BookingPage Continue_Booking() {
		op.clickThis(continueBtn, "Continue button");
		CreateDriver.getInstance().getDriver().switchTo().activeElement();
		op.clickThis(continueAsGuestlink, "Continue as Guest");
		return new BookingPage();
	}
	
}

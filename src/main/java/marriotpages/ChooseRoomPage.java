package marriotpages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;


import drivercreation.CreateDriver;




public class ChooseRoomPage extends BasePage {
	

	public ChooseRoomPage() {
		PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
		//ExtentTest node = CreateDriver.getInstance().testLogger.get().createNode(this.getClass().getSimpleName());
		//CreateDriver.getInstance().nodeLog.set(node);
	}
	
/******************* WebElement Declaration ***********************************/
	
	@FindBy(xpath="//div[@id='tab1']//div[contains(@class,'l-room-type-category-section')]//div[1]//div[1]//div[3]//div[1]//div[2]//div[2]//button[1]")
						private WebElement selectBtn;
	
	
	
public ReservationDetailsPage Select_Room() {
	op.clickThis(selectBtn, "select button");
	return new ReservationDetailsPage();
}
	

}

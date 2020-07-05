package marriotpages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import drivercreation.CreateDriver;


public class BookingPage extends BasePage {
	
	public BookingPage() {
		PageFactory.initElements(CreateDriver.getInstance().getDriver(), this);
		//ExtentTest node = CreateDriver.getInstance().testLogger.get().createNode(this.getClass().getSimpleName());
		//CreateDriver.getInstance().nodeLog.set(node);
	}
	
/******************* WebElement Declaration ***********************************/
	@FindBy(name="firstName1") private WebElement fname;
	@FindBy(name = "lastName1")private WebElement lname;
	
	
	public void Provide_Guest_Info() {
		op.writeText(fname, "guest name", "First name");
		op.writeText(lname, "guest lname", "Last Name");
		
	}
	

}

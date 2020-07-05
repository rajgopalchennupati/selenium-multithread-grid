package marriotpages;


import org.testng.annotations.Test;


public class FullTest extends BaseTest {
	
	@Test
	public void SearchLondon() {
		
		home
			.enterDestination(location.get("London"))
			.findHotels()
			.verifyDestination("London, UK")
			.Get_Rates()
			.Select_Room()
			.Continue_Booking()
			.Provide_Guest_Info();
			
			
	}
	
	//@Test
	public void SignIn() {
		home 
			.signIn(username.get("invalid"), "passwrd");
	}
	
	
	@Test
	public void SearchParis() {
		
		home
			.enterDestination(location.get("Paris"))
			.findHotels()
			.verifyDestination("Paris, France")
			.Get_Rates()
			.Select_Room();
		
					
	}

}

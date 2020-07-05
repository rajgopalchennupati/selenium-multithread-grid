package marriotpages;

import marriotpages.CustomAssert;
import marriotpages.Operation;

public class BasePage {
	
	public Operation op;
	public CustomAssert check;
	
	BasePage(){
		
        op = new Operation();
		
		check = new CustomAssert();
	}

}

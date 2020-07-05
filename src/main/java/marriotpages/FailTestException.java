package marriotpages;

import drivercreation.CreateDriver;

public class FailTestException extends RuntimeException {
	
	public FailTestException(Throwable throwable) {
		CreateDriver.getInstance().getExtentTestLogger().fail(throwable);
	}

	public FailTestException(String exception) {
		// TODO Auto-generated constructor stub
		CreateDriver.getInstance().getExtentTestLogger().fail(exception);
	}

}

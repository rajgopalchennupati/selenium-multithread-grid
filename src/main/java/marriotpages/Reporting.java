package marriotpages;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting {
	
	static ExtentHtmlReporter htmlReport;
	protected static ExtentReports report;
	static ExtentTest testCase;
	
	public void initializeReport() {
		String reportPath = System.getProperty("user.dir")+"/extentreports/report.html";
		
		File file = new File(reportPath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		htmlReport = new ExtentHtmlReporter(reportPath);
		htmlReport.config().setDocumentTitle("MultiThreadingReport");
		htmlReport.config().setReportName("Automation Report");
		
		report = new ExtentReports();
		report.attachReporter(htmlReport);
				
	}
	
	public void generateReport() {
		report.flush();
	}

}

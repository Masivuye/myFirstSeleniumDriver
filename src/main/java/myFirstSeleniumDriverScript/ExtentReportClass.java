package myFirstSeleniumDriverScript;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportClass {
	ExtentReports extent;    // Create an instance of Extent Reports
	ExtentHtmlReporter htmlReporter; // Create an instance of the htmlReport and we will add this to the extent reports instance
	ExtentTest logger; // Create an instance of logger to log test steps to the report
	
	
	// Random Date and Timestamp generator
	public String generateDateTimeStamp() {
			
			Date  dt = new Date();
			
//			Timestamp ts = new Timestamp (dt.getTime());
			
			return new SimpleDateFormat("yyyy-dd-mm_hhmmss").format(dt);
			
//			return ts.toString();
		}
		
	// Create a method to configure the report and set it up for us
	public void startReport(String pDocTitle, String pReportName) {
		// Assigned a new instance of ExtentReports to the extent variable
		this.extent = new ExtentReports();
		
		// Create the location and filename for the html report
		this.htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//ExtentReport" + generateDateTimeStamp() + ".html");
		// LG: This takes the timestamp out of the file name 
		//this.htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//ExtentReport"+ ".html");
		// Add HTMLReporter to the extent report instance
		this.extent.attachReporter(this.htmlReporter);
		
		// Configure some of the properties 
		this.extent.setSystemInfo("Host Name", "Sastri's Mac");
		this.extent.setSystemInfo("Environment", "Inspired Testing Academy");
		this.extent.setSystemInfo("User Name", "Sastri");
		
		// Title for the Test Report
		this.htmlReporter.config().setDocumentTitle(pDocTitle);
		
		// Set the Report name
		this.htmlReporter.config().setReportName(pReportName);
		
		// Set the Dark Theme for Extent Reports
		this.htmlReporter.config().setTheme(Theme.DARK);
		
//		// Set the Logger to a new test
//		this.logger = this.extent.createTest("Test1");
//		
	}
	
	public void createTest(String sName) {
		// Set the Logger to a new test
		this.logger = this.extent.createTest(sName);
		//logger = extent.createTest(sName);
	
	}
	// Log Pass to Report
	public void logPass(String pActual, String pExpectedValue) {
		this.logger.log(Status.PASS, MarkupHelper.createLabel("The test has passed. Expected Value: " + pExpectedValue 
				+ " || Actual Value: " + pActual , ExtentColor.GREEN));
	}
	
	// Log Fail to Report
	public void logFail(String pActual, String pExpectedValue) {
		this.logger.log(Status.FAIL, MarkupHelper.createLabel("The test has failed. Expected Value: " + pExpectedValue 
				+ " || Actual Value: " + pActual , ExtentColor.RED));

	}
	
	// Close and Finalise the Test Report
	public void closeReport() {
		this.extent.flush();
	}

	
	public  String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir") +"//Images//" + System.currentTimeMillis()
		+ ".png");
		String errflpath = Dest.getAbsolutePath();
		Files.copy(scrFile.toPath(), Dest.toPath());
		return errflpath;
	}
	
	public void logScreenshot(WebDriver driver) throws IOException {
		this.logger.addScreenCaptureFromPath(capture(driver));
	}

}

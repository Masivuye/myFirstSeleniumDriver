package myFirstSeleniumDriverScript;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class ReportClass {
	private ExtentReportClass exReports = new ExtentReportClass();
	
	public void DoValidations(String sExpectedValue, String sActualValue ) {
		if(sExpectedValue.contentEquals(sActualValue)) {
			System.out.println("Test Passed. Expected Value: " + sExpectedValue + " Actual value " + sActualValue);
			
		}
		else {
			System.out.println("Test Failed. Expected Value: " + sExpectedValue + " Actual value " + sActualValue);
		}
		
	}
	public void logScreenshot(WebDriver driver) throws IOException {
	exReports.logScreenshot(driver);
	}
	
	public void FinaliseExtentReport() {
		exReports.closeReport();
	}
}

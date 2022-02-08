package myFirstSeleniumDriverScript;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

// Mongo DB imports
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import junit.framework.Assert;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;



// This class manages all Selenium Functions and the Driver
public class SeleniumFunction {
	// Class Private Variables
	private  WebDriver driver;
	ExtentReportClass extReports = new ExtentReportClass();
	public String gatewayurl;
	
	
	//private ReportingClass reports = new ReportingClass();
	
	
	// Constructor
	//Default Constructor
	
	public SeleniumFunction()  {
		
		// Tell Java where the chromedriver.exe sits & Create a new instance of the Driver
		//String driverName;
		SetupSelenium();
		
	}

	public void SetupSelenium() {
		
			
		/* Properties setup
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("dataConfig.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			p.load(is);
		} catch (IOException e) {

			e.printStackTrace();
		}*/
		
		System.setProperty("webdriver.chrome.driver",
	            "C:\\Users\\chromedriver_win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
	
		
		//System.setProperty("webdriver."+driverName+".driver",p.getProperty("driverdir")+driverName+"driver.exe");

		// For Mac
		//System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

		// determine which driver to use
		
		// Here @Sastri
		/*if (driverName.contains("chrome")) {
			this.driver = new ChromeDriver();	
			}
		else { 
			this.driver = new FirefoxDriver();
		}
			

		
		// set the implicit wait
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
	}*/
	}
	
	//wait 
	public void waitForAlert(int elementWait) {
		WebDriverWait wait = new WebDriverWait(this.driver,elementWait);
    	wait.until(ExpectedConditions.alertIsPresent());
	}


	public void startReport(String sReportName, String sTitle) {
		extReports.startReport(sReportName, sTitle);
	}
	
	public void createTest(String sName) {
		extReports.createTest(sName);
	}
	
	// Reusable method to perform validations for us
	public void doValidation (String sActualValue, String sExpectedValue) {
		
		// Output to Console and Extent Report
		if (sActualValue.contentEquals(sExpectedValue)) {
			// If condition is true, execute this code
			// output to console
			System.out.println("Test passed. Expected Value = " + sExpectedValue + " Actual Value = " + sActualValue);
			
			// output to extent Reports
			extReports.logPass(sActualValue, sExpectedValue);
			
		}
		else {
			// If condition is false, execute this code
			System.out.println("Test failed. Expected Value = " + sExpectedValue + " Actual Value = " + sActualValue);
			
			// output to extent Reports
			extReports.logFail(sActualValue, sExpectedValue);
			
		}

	}
	

	// Get for chromedriver
	public WebDriver getDriver() {
		return this.driver;
				
	}
	
	// Set for chromedriver
	public void setDriver(WebDriver pDriver) {
		this.driver = pDriver;
	}
	
	
	// function to populate dropdown and validate the text
	public void populateDropDown (String pByName, String pValue) {
		// Creates an instance of the dropdown object
		Select sDrpDown = new Select (this.driver.findElement(By.name(pByName)));
		
		// Populates the Dropdown
		sDrpDown.selectByVisibleText(pValue);
		
		WebElement selectedoption = sDrpDown.getFirstSelectedOption();
		String sActualValue = selectedoption.getText();
		String sExpectedValue = pValue;
		//this.doValidation(sActualValue, sExpectedValue);
	}
	
	public void updateReport (String sActualValue, String sExpectedValue) {
		
		this.doValidation(sActualValue, sExpectedValue);
	}
	
	// function to populate an input field using multiple By clauses
	public void populateInputField(By byClause, String inputValue) {
		this.driver.findElement(byClause).sendKeys(inputValue);
		
		// Reads the value that was typed into the field
		//String sActualValue = this.driver.findElement(byClause).getText();
		String sActualValue = this.driver.findElement(byClause).getAttribute("value");
		
		// set the value for ExpectedValue 
		String sExpectedValue = inputValue;
		
		//this.doValidation(sExpectedValue, sActualValue);
		
	}
	
	// function to select a radio button option
	public void selectRadioOption(String pValue) {
		driver.findElement(By.xpath("//input[@type='radio'][@value='" + pValue + "']")).click();
	}
	
	// function to click on a hyperlink
	public void clickLink(String pLinkText) {
		driver.findElement(By.linkText(pLinkText)).click();
		
		// in future we will add validations and screenshots to this method
	}
	
	
	public void logScreenShot() throws IOException {
		this.extReports.logScreenshot(this.driver);
	}
	

	public void CloseSelenium() {
		//this.reports.FinaliseExtentReport();
		////driver.close will close the original browser window
		//this.driver.close();
		////driver.quit will close the original and all subsequent browser tabs
		this.driver.quit();
		// Also finalise and close the test report for us
		extReports.closeReport();
	}
	
	// Switch between tabs
	public void switchTab(int pTagIndex) {
		//Hold all the window handles in an array list
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		if (pTagIndex < 0) {
			
			pTagIndex = newTb.size() - 1;
		}
		driver.switchTo().window(newTb.get(pTagIndex));
	}
	
	// Get last Digits
	public String getLastDigits(int iLastDigits, String pStringName) {
		String sLastdigits = pStringName.substring(pStringName.length() - iLastDigits);
	    System.out.println(sLastdigits);
	    return sLastdigits;
	}
	
	// Get first Digits
	public String getFirstDigits(int iFirstDigits, String pStringName) {
		String sFirstdigits = pStringName.substring(0,iFirstDigits);
	    System.out.println(sFirstdigits);
	    return sFirstdigits;
	}
	
	// Maximise Browser Window
	public void maximiseBrowserWindow() {
		driver.manage().window().maximize();
	}
	
	// Get Detail
	public String getDetail(String sCssOfField) throws IOException {
		// get field text
		String sFieldText = this.driver.findElement(By.cssSelector(sCssOfField)).getText();
		// print but remove this later
	    System.out.println("getDetail: " + sFieldText);
	    // return
		return sFieldText;
	}
	
    public String readPDFContent(String appUrl, int expectedNoPages) throws Exception {

        URL url = new URL(appUrl);
        InputStream input = url.openStream();
        BufferedInputStream fileToParse = new BufferedInputStream(input);
        PDDocument document = null;
        String output = null;

        try {
            document = PDDocument.load(fileToParse);
            output = new PDFTextStripper().getText(document);
            // ensure the number of pages is correct
            int numberOfPages = getPageCount(document);
            Assert.assertEquals(numberOfPages,expectedNoPages);
     
            
        } finally {
            if (document != null) {
                document.close();
            }
            fileToParse.close();
            input.close();
        }
        return output;
    }
    
    public static int getPageCount(PDDocument doc) {
		//get the total number of pages in the pdf document
		int pageCount = doc.getNumberOfPages();
		return pageCount;
		
	}
    
    //merge from master to branch
	

}



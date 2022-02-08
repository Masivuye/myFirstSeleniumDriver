package myFirstSeleniumDriverScript;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BookBus{
	
	SeleniumFunction selenium = new SeleniumFunction();
	ReportClass report = new ReportClass();
	
	public static WebDriverWait wait;
	String sExpectedValue = "";
	String sActualValue = "";
	

	WebDriver driver = selenium.getDriver();
	
	
		
	public  void NavigateToURL(String sURL) {
		driver.get(sURL);
		
	}
	public  void SelectTicketType() {
		WebElement ele = driver.findElement(By.xpath("(//input[@name='TicketType'])"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		
	}
	public  void CaptureDetails(String sTitle,String sInitials, String sSurname, String sIdNo, String sEmail, String sCellno) {
		
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"PassengerList_0__STTitle\"]")));
	       Select drpdownTitle = new Select ( driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STTitle\"]")));
			drpdownTitle.selectByVisibleText(sTitle);
			
			driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STInitials\"]")).sendKeys(sInitials);
			 sActualValue = driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STInitials\"]")).getAttribute("value");
				sExpectedValue = sInitials;
				report.DoValidations(sExpectedValue, sActualValue);
			
			driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STSurname\"]")).sendKeys(sSurname);
			sActualValue = driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STSurname\"]")).getAttribute("value");
			sExpectedValue = sSurname;
			report.DoValidations(sExpectedValue, sActualValue);
			
			driver.findElement(By.xpath("//*[@id=\"inputPassport_0\"]")).sendKeys(sIdNo);
			sActualValue =driver.findElement(By.xpath("//*[@id=\"inputPassport_0\"]")).getAttribute("value");
			sExpectedValue = sIdNo;
			report.DoValidations(sExpectedValue, sActualValue);
			
			driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STEMail\"]")).sendKeys(sEmail);
			sActualValue = driver.findElement(By.xpath("//*[@id=\"PassengerList_0__STEMail\"]")).getAttribute("value");
			sExpectedValue = sEmail;
			report.DoValidations(sExpectedValue, sActualValue);
			
			driver.findElement(By.xpath("//*[@id=\"inputCell_0\"]")).sendKeys(sCellno);
			sActualValue = driver.findElement(By.xpath("//*[@id=\"inputCell_0\"]")).getAttribute("value");
			sExpectedValue = sCellno;
			report.DoValidations(sExpectedValue, sActualValue);
			
	}
	public  void SelectDropdown(String sFrom, String sTo) {
		Select drpdownFrom = new Select ( driver.findElement(By.name("From")));
		drpdownFrom.selectByVisibleText(sFrom);
		
		sActualValue = driver.findElement(By.name("From")).getAttribute("value");
		sExpectedValue = sFrom;
		report.DoValidations(sActualValue, sExpectedValue);
			
		Select drpdownTo = new Select ( driver.findElement(By.name("Destination")));
		drpdownTo.selectByVisibleText(sTo);
		
		sActualValue = driver.findElement(By.name("Destination")).getAttribute("value");
		sExpectedValue = sTo;
		report.DoValidations(sActualValue, sExpectedValue);
		
	}
	public  void SelectDate() {

		WebElement selectDate = driver.findElement(By.xpath("//input[@id='StartDate']"));
		selectDate.click();
		
       WebElement allDate = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]"));
       allDate.click();
		
	}
	public  void NoOfPeople(String sAdults, String sChildren, String sInfants) {
		
		Select drpdownAdults = new Select ( driver.findElement(By.name("Adults")));
	       drpdownAdults .selectByVisibleText(sAdults);
	       
	       sActualValue = driver.findElement(By.name("Adults")).getAttribute("value");
			sExpectedValue = sAdults;
			report.DoValidations(sExpectedValue, sActualValue);
	       
			
	       Select drpdownChildren = new Select ( driver.findElement(By.name("Children")));
	       drpdownChildren .selectByVisibleText(sChildren);
	       
	       sActualValue = driver.findElement(By.name("Children")).getAttribute("value");
			sExpectedValue = sChildren;
			report.DoValidations(sExpectedValue, sActualValue);
	       
	       Select drpdownInfants = new Select ( driver.findElement(By.name("Infants")));
	       drpdownInfants .selectByVisibleText(sInfants);
	       
	       sActualValue = driver.findElement(By.name("Infants")).getAttribute("value");
			sExpectedValue = sInfants;
			report.DoValidations(sExpectedValue, sActualValue);
		
	}
	public  void FindCoach() {
		driver.findElement(By.xpath("//input[@value='Find Coach']")).click();
		
	}
	public  void BookTickets() {
		    wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id= 'btnBookTickets']")));
			
	       driver.findElement(By.xpath("//input[@id= 'btnBookTickets']")).click();
		
		
	}
	public  void SaveTravellers(String sPassed, String sFailed) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"btnSaveTravellers\"]")).click();
		String sExpectedButton = driver.findElement(By.xpath("//*[@id=\"btnSaveTravellers\"]")).getAttribute("value");
		if(sExpectedButton!=null) {
			System.out.println(sPassed);
			
		}
		else {
			System.out.println(sFailed);
		}
		Thread.sleep (5000);
       
	}
	public  void CloseDriver() {
		driver.close();
	}
	
	

}

package myFirstSeleniumDriverScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class NewTours {
	
	public static void main(String[] args) throws InterruptedException {
		
		//input test data
		String sURL = "http://demo.guru99.com";
		String sUsername = "marcury";
		String sPassword = "mercury";
		String sPassCount = "2";
		String sFromPort = "London";
		String sFromMonth = "August";
		String sFromDay = "20";
		String sToPort = "Paris";
		String sToMonth = "October";
		String sToDay = "25";
		String sAirline = "Unified Airlines";
		String sFlightType = "''First'";
		
		System.setProperty("webdriver.chrome.driver",
	            "C:\\Users\\masivuye.HST1\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver = new ChromeDriver();
		
		driver.get(sURL);
		
		driver.findElement(By.linkText("New Tours")).click();
		
		
		
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(sUsername);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(sPassword);
		
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		
		driver.findElement(By.linkText("Flights")).click();
		
		driver.findElement(By.xpath("//input[@value='oneway']")).click();
		
		Select drpdownPassenger = new Select ( driver.findElement(By.name("passCount")));
		drpdownPassenger.selectByVisibleText(sPassCount);
		
		Select drpdownFromPort = new Select ( driver.findElement(By.name("fromPort")));
		drpdownFromPort.selectByVisibleText(sFromPort);
		
		Select drpdownFromMonth = new Select ( driver.findElement(By.name("fromMonth")));
		drpdownFromMonth.selectByVisibleText(sFromMonth);
		
		Select drpdownFromDay = new Select ( driver.findElement(By.name("fromDay")));
		drpdownFromDay.selectByVisibleText(sFromDay);
		
		Select drpdownToPort = new Select ( driver.findElement(By.name("toPort")));
		drpdownToPort.selectByVisibleText(sToPort);
		
		Select drpdownToMonth = new Select ( driver.findElement(By.name("toMonth")));
		drpdownToMonth.selectByVisibleText(sToMonth);
		
		Select drpdownToDay = new Select ( driver.findElement(By.name("toDay")));
		drpdownToDay.selectByVisibleText(sToDay);
		
		Select drpdownAirline = new Select ( driver.findElement(By.name("airline")));
		drpdownAirline.selectByVisibleText(sAirline);
		
		//driver.findElement(By.xpath("//input[@value='First']")).click();
		// Multiple attributes
		driver.findElement(By.xpath("//input[@name='servClass'][@value=" + sFlightType + "]")).click();
		
		driver.findElement(By.xpath("//input[@name='findFlights']")).click();
		
		Thread.sleep (5000);
		
		driver.close();
		
		
		
		
		
		
		
	}
	

}

package myFirstSeleniumDriverScript;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;



public class FBELogIn {
	public static WebDriverWait wait;
	
	public static void main(String[] args) throws InterruptedException {
		
		String sURL = "https://hstfbe.hstoutsystems.com/freebedenquiry/#/";
		String sUsername = "Masifbe";
		String sPassword = "Masi@fbe3";
		String sLinkText = "Tygerberg Hospitaal (DOCTOR)";
		String sFailed = "Test Failed";
		String sPassed = " Test Passed";
		String sActualValue = "";
		String sExpectedValue = "";
		
		
		System.setProperty("webdriver.chrome.driver",
	            "C:\\Users\\masivuye.HST1\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver = new ChromeDriver();
		
		driver.get(sURL);
		
		driver.navigate().refresh();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']")));
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(sUsername);
		sActualValue = driver.findElement(By.xpath("//input[@name='username']")).getAttribute("value");
		sExpectedValue = sUsername;
		
		if(sUsername.contentEquals(sActualValue)) {
				System.out.println("Test Passed. Expected Value: " + sExpectedValue + " Actual value " + sActualValue);
		
		}
		else {
			System.out.println("Test Failed. Expected Value: " + sExpectedValue + " Actual value " + sActualValue);
		}
		

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(sPassword);
		sActualValue = driver.findElement(By.xpath("//input[@name='password']")).getAttribute("value");
		sExpectedValue = sPassword;
		
		if(sPassword.contentEquals(sActualValue)) {
				System.out.println("Test Passed. Expected Value: " + sExpectedValue + " Actual value " + sActualValue);
		
		}
		else {
			System.out.println("Test Failed. Expected Value: " + sExpectedValue+ " Actual value " + sActualValue);
		}
		
		
		driver.findElement(By.xpath("//form[@id='signInForm']/div[4]/button")).click();
		
		driver.findElement(By.linkText(sLinkText)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(.,'Normal User')]")));
		
		List<WebElement> list = driver.findElements(By.xpath("//p[contains(.,'Normal User')]"));
		//Assert.isTrue(true,"Normal User", list.size() > 0);
		
		if(driver.findElements(By.xpath("//p[contains(.,'Normal User')]"))!= null) {
			
			System.out.println(sPassed);
		}
		else {
		System.out.println(sFailed);
		}
		Thread.sleep(50000);
		//driver.close();
		
		
		
	}

}

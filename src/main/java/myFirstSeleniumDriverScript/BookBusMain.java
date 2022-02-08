package myFirstSeleniumDriverScript;


public class BookBusMain {
	
	public static void main(String[] args) throws InterruptedException  
	{
		BookBus bus = new BookBus();
		
		
		
		 String sURL = "https://res.prasa.com/citytocity/booking";
		 String sFrom = "Mthatha";
		 String sTo	= "Capetown";
		//String sStartDate = "2022/02/23";
		 String sAdults = "1";
		 String sChildren = "0";
		 String sInfants = "0";
		 String sTitle = "Miss";
		 String sInitials = "M";
		 String sSurname = "Bheme";
		 String sIdNo = "9803050956080";
		 String sEmail = "masivuye@gmail.com";
		 String sCellno = "0747803013";
		 String sFailed = "Test Failed";
		 String sPassed = " Test Passed";
		
		
		// Navigate to URL
		bus.NavigateToURL(sURL);
		bus.SelectTicketType();
		bus.SelectDropdown(sFrom, sTo);
		bus.SelectDate();
		bus.NoOfPeople(sAdults, sChildren, sInfants);
		bus.FindCoach();
		bus.BookTickets();
		bus.CaptureDetails(sTitle, sInitials, sSurname, sIdNo,  sEmail,sCellno);
		bus.SaveTravellers(sPassed, sFailed);
		
		
	
		Thread.sleep (5000);
		
		
	}

}

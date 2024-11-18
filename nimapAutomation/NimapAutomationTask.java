package nimapAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NimapAutomationTask {

	WebDriver driver;
	//Task--1
	
	@BeforeClass
	void SetUp() {
	 driver=new ChromeDriver();
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
	}
	
	@Test(priority=1, dataProvider="dp")
	void login(String email,String password) {
		 driver.get("https://testffc.nimapinfotech.com/dashboard");
		 driver.manage().window().maximize();
		 driver.findElement(By.cssSelector("input#mat-input-0")).sendKeys(email);   //Enter Mail
		 driver.findElement(By.cssSelector("input#mat-input-1")).sendKeys(password); //Enter Password
	
		String CapacheText=driver.findElement(By.xpath("//canvas[@id='captcahCanvas']")).getText();
	    System.out.println(CapacheText);
	    
	    driver.findElement(By.xpath("//input[@class='ng-untouched ng-pristine ng-valid']")).sendKeys(CapacheText);
	    driver.findElement(By.xpath("//button[@id='kt_login_signin_submit']")).click();// submit
	  
	}
	
	@DataProvider(name="dp")
	Object[][] Logindata(){
		Object data[][]= {
			         	{"gwagh2708@gmail.com","Gauri@12345"}
			         };
		return data;
	}
	
	//Task-- 2
	@Test(priority=2 , dependsOnMethods= {"login"})
	void PunchlnPopup() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Punch In']")).click();
		driver.switchTo().alert().accept();  //click on ok Button
	}
	
	
	
	//Task--3
	@Test(priority=3)
	void AddCostumer() {
		driver.findElement(By.xpath("//span[normalize-space()='My Customers']")).click();  //Click on My Costumer
		driver.findElement(By.xpath("//span[normalize-space()='New Customer']")).click(); //click on new Costumer
	    
		//Neww Costemer Details
		driver.findElement(By.xpath("//input[@id='mat-input-143']")).sendKeys("Gauri wagh");  //Costemer Name
		driver.findElement(By.xpath("//input[@id='mat-input-144']")).sendKeys("123");  //ref No
		driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c77-305']")).sendKeys("Komal");//reference By
		driver.findElement(By.xpath("//input[@id='mat-input-166']")).sendKeys("Gauri");//contact person name
		driver.findElement(By.xpath("//input[@id='mat-input-167']")).sendKeys("9856453498"); //Mobile No
		driver.findElement(By.xpath("//input[@id='mat-input-168']")).sendKeys("89654564");	 //Telephone No
	    driver.findElement(By.xpath("//input[@id='mat-input-169']")).sendKeys("gwagh2708@gmail.com");  //Email
	    driver.findElement(By.xpath("//input[@id='mat-input-170']")).sendKeys("QA");  //Contact person Designation
	    driver.findElement(By.xpath("//body/div[@class='cdk-overlay-container']/div[1]")).sendKeys("Manmad, Tal- Nandgaon, District- Nashik"); //Address
       driver.findElement(By.xpath("//span[normalize-space()='Save']"))	.click(); //Click on save
	}
	
	@AfterClass
	void TearDown() {
		driver.quit();
	}
	
	
	
	
}

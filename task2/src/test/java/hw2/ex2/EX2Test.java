package hw2.ex2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EX2Test {

private WebDriver driver; 
	
	@BeforeTest
	public void configTest(){
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	}
	
	@Test
	public void openPageTest(){		
    //1.Open test site by URL
	driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
	driver.manage().window().maximize();
	
	Assert.assertEquals(driver.getCurrentUrl(),"https://jdi-testing.github.io/jdi-light/index.html");
	
	//2.Assert Browser title
	Assert.assertEquals(driver.getTitle(), "Home Page");
	}
	
	@Test(dependsOnMethods="openPageTest")
	public void loginTest() {
	//3.Perform login
	driver.findElement(By.id("user-icon")).click();	
	
	WebElement login = driver.findElement(By.id("name"));
	WebElement password = driver.findElement(By.id("password"));
	
	login.sendKeys("Roman");
	password.sendKeys("Jdi1234");
	
	driver.findElement(By.id("login-button")).click();
	
	//4.Assert User name in the left-top side of screen that user is loggined
	WebElement username = driver.findElement(By.id("user-name"));
	
	SoftAssert soft = new SoftAssert();
	
	soft.assertEquals("ROMAN IOVLEV", username.getText());
	soft.assertTrue(username.isDisplayed());
	soft.assertAll();
	}
	
	@Test(dependsOnMethods="loginTest")
	public void serviceHeaderTest() {
	//5.Click on "Service" subcategory in the header and check that drop down contains options
		driver.findElement(By.className("dropdown-toggle")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='dropdown-menu']/li"));
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertEquals(elements.size(),9);
		
		for(int i=0; i<elements.size();i++) {
			soft.assertTrue(elements.get(i).isDisplayed());
		}
		
		soft.assertEquals(elements.get(0).getText(),"SUPPORT");
		soft.assertEquals(elements.get(1).getText(),"DATES");
		soft.assertEquals(elements.get(2).getText(),"SEARCH");
		soft.assertEquals(elements.get(3).getText(),"COMPLEX TABLE");
		soft.assertEquals(elements.get(4).getText(),"SIMPLE TABLE");
		soft.assertEquals(elements.get(5).getText(),"USER TABLE");
		soft.assertEquals(elements.get(6).getText(),"TABLE WITH PAGES");
		soft.assertEquals(elements.get(7).getText(),"DIFFERENT ELEMENTS");
		soft.assertEquals(elements.get(8).getText(),"PERFORMANCE");
		
		soft.assertAll();	
	}
	
	@Test(dependsOnMethods="serviceHeaderTest")
	public void serviceSideTest() {
	//6.Click on Service subcategory in the left section and check that drop down contains options
		driver.findElement(By.className("menu-title")).click();	
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='sub']/li"));
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertEquals(elements.size(),9);
		
		for(int i=0; i<elements.size();i++) {
			soft.assertTrue(elements.get(i).isDisplayed());
		}
		
		soft.assertEquals(elements.get(0).getText(),"Support");
		soft.assertEquals(elements.get(1).getText(),"Dates");
		soft.assertEquals(elements.get(2).getText(),"Complex Table");
		soft.assertEquals(elements.get(3).getText(),"Simple Table");
		soft.assertEquals(elements.get(4).getText(),"Search");
		soft.assertEquals(elements.get(5).getText(),"User Table");
		soft.assertEquals(elements.get(6).getText(),"Table with pages");
		soft.assertEquals(elements.get(7).getText(),"Different elements");
		soft.assertEquals(elements.get(8).getText(),"Performance");
		
		soft.assertAll();	
	}
	
	@Test(dependsOnMethods="serviceSideTest")
	public void differentElementsTest() {
	//7.Open through the header menu Service -> Different Elements Page
		driver.findElement(By.xpath("//*[@class='sub']/li[8]")).click();
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertEquals(driver.getCurrentUrl(),"https://jdi-testing.github.io/jdi-light/different-elements.html");
		soft.assertEquals(driver.getTitle(), "Different Elements");
		
	//8.Check interface on Different elements page, it contains all needed elements
		List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));
		List<WebElement> radios = driver.findElements(By.xpath("//*[@type='radio']"));
		List<WebElement> dropdown = driver.findElements(By.tagName("select"));
		List<WebElement> buttons = driver.findElements(By.xpath("//*[@class='uui-button']"));
		
		soft.assertEquals(checkboxes.size(),4);
		soft.assertEquals(radios.size(),4);
		soft.assertEquals(dropdown.size(),1);
		soft.assertEquals(buttons.size(),2);	
		
	//9.Assert that there is Right Section
		soft.assertTrue(driver.findElement(By.xpath("//*[@name='log-sidebar']")).isDisplayed());
		
	//10.Assert that there is Left Section
		soft.assertTrue(driver.findElement(By.id("mCSB_1")).isDisplayed());
		
		soft.assertAll();	
	}
	
	@Test(dependsOnMethods="differentElementsTest")
	public void selectBoxesTest() {
	//11.Select checkboxes
		List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));
		
		WebElement water = checkboxes.get(0);
		WebElement wind = checkboxes.get(2);
		
		water.click();
		wind.click();
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertTrue(water.isSelected());
		soft.assertTrue(wind.isSelected());
		
	//12.Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox
		List<WebElement> logs = driver.findElements(By.xpath("//*[@class='panel-body-list logs']/li"));
		
		soft.assertEquals(logs.size(), 2);
		
        for(int i=0;i<logs.size();i++) {
        	soft.assertTrue(logs.get(i).isDisplayed());
        }
		
		String windlog = logs.get(0).getText();
		String waterlog = logs.get(1).getText();
		
		soft.assertTrue(waterlog.contains("Water")&&(waterlog.contains("true")));
		soft.assertTrue(windlog.contains("Wind")&&(windlog.contains("true")));
		
		soft.assertAll();
	}
	
	@Test(dependsOnMethods="selectBoxesTest")
	public void radioTest() {
	//13.Select radio	
		List<WebElement> radios = driver.findElements(By.xpath("//*[@type='radio']"));
		
		WebElement selen = radios.get(3);
		
		selen.click();
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertTrue(selen.isSelected());
		
	//14.Assert that for radiobutton there is a log row and value is corresponded to the status of radiobutton. 
		List<WebElement> logs = driver.findElements(By.xpath("//*[@class='panel-body-list logs']/li"));
		
		soft.assertEquals(logs.size(), 3);
		
        for(int i=0;i<logs.size();i++) {
        	soft.assertTrue(logs.get(i).isDisplayed());
        }
		
		String selenlog = logs.get(0).getText();
				
		soft.assertTrue(selenlog.contains("Selen")&&(selenlog.contains("metal")));
		
		soft.assertAll();
	}
	
	@Test(dependsOnMethods="radioTest")
	public void dropdownTest() {
	//15.Select in dropdown
		WebElement dropdown = driver.findElement(By.tagName("select"));
		
		dropdown.click();
		
		WebElement yellow = driver.findElement(By.xpath("//select/option[last()]"));
		
		yellow.click();
		
        SoftAssert soft = new SoftAssert();
		
		soft.assertTrue(yellow.isSelected());
		
	//16.Assert that for dropdown there is a log row and value is corresponded to the selected value
		List<WebElement> logs = driver.findElements(By.xpath("//*[@class='panel-body-list logs']/li"));
		
        soft.assertEquals(logs.size(), 4);
        
        for(int i=0;i<logs.size();i++) {
        	soft.assertTrue(logs.get(i).isDisplayed());
        }
		
		String yellowlog = logs.get(0).getText();
		
		soft.assertTrue(yellowlog.contains("Colors")&&(yellowlog.contains("Yellow")));
		
		soft.assertAll();	
	}
	
	@Test(dependsOnMethods="dropdownTest")
	public void unselectBoxesTest() {
    //17.Unselect and assert checkboxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));
		
		WebElement water = checkboxes.get(0);
		WebElement wind = checkboxes.get(2);
		
		water.click();
		wind.click();
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertFalse(water.isSelected());
		soft.assertFalse(wind.isSelected());
		
	//18.Assert that for each checkbox there is an individual log row and value is corresponded to the status of checkbox	
        List<WebElement> logs = driver.findElements(By.xpath("//*[@class='panel-body-list logs']/li"));
		
        soft.assertEquals(logs.size(), 6);
        
        for(int i=0;i<logs.size();i++) {
        	soft.assertTrue(logs.get(i).isDisplayed());
        }
        
        String windlog = logs.get(0).getText();
		String waterlog = logs.get(1).getText();
		
		soft.assertTrue(waterlog.contains("Water")&&(waterlog.contains("false")));
		soft.assertTrue(windlog.contains("Wind")&&(windlog.contains("false")));
		
        soft.assertAll();	
	}
	
	@AfterTest
	public void closeSession(){
     	driver.close();
	}

}

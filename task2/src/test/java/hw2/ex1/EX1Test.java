package hw2.ex1;

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


public class EX1Test {

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
	String username = driver.findElement(By.id("user-name")).getText();
	Assert.assertEquals("ROMAN IOVLEV", username);
	
	//5.Assert Browser title
	Assert.assertEquals(driver.getTitle(), "Home Page");
	}
	
	@Test(dependsOnMethods="loginTest")
	public void headerItemTest() {
	//6.Assert that there are 4 items on the header section are displayed and they have proper texts	
	 List<WebElement> header= driver.findElements(By.xpath("//*[@class='uui-navigation nav navbar-nav m-l8']/li"));
	 Assert.assertEquals(header.size(),4);
	 
	 Assert.assertEquals("HOME", header.get(0).getText());
	 Assert.assertTrue(header.get(0).isDisplayed());
	 
	 Assert.assertEquals("CONTACT FORM", header.get(1).getText());
	 Assert.assertTrue(header.get(1).isDisplayed());
	 
	 Assert.assertEquals("SERVICE", header.get(2).getText());
	 Assert.assertTrue(header.get(2).isDisplayed());
	 
	 Assert.assertEquals("METALS & COLORS", header.get(3).getText());
	 Assert.assertTrue(header.get(3).isDisplayed());
	}
	
	@Test(dependsOnMethods="headerItemTest")
	public void imagesTest() {
	//7.Assert that there are 4 images on the Index Page and they are displayed
		List<WebElement> imagesList = driver.findElements(By.xpath("//*[contains(@class,'benefit-icon')]"));
		
		Assert.assertEquals(4,imagesList.size());
		Assert.assertTrue(imagesList.get(0).isDisplayed());
		Assert.assertTrue(imagesList.get(1).isDisplayed());
		Assert.assertTrue(imagesList.get(2).isDisplayed());
		Assert.assertTrue(imagesList.get(3).isDisplayed());
	}
	
	@Test(dependsOnMethods="imagesTest")
	public void textTest() {
	//8.Assert that there are 4 texts on the Index Page under icons and they have proper text	
        List<WebElement> textList=driver.findElements(By.xpath("//*[contains(@class,'benefit-txt')]"));
        
        Assert.assertEquals(4, textList.size());
        Assert.assertEquals(textList.get(0).getText(),"To include good practices\n"
        		+ "and ideas from successful\n"
        		+ "EPAM project");
        Assert.assertTrue(textList.get(0).isDisplayed());
        
        Assert.assertEquals(textList.get(1).getText(),"To be flexible and\n"
        		+ "customizable");
        Assert.assertTrue(textList.get(1).isDisplayed());
        
        Assert.assertEquals(textList.get(2).getText(),"To be multiplatform");        
        Assert.assertTrue(textList.get(2).isDisplayed());
        
        Assert.assertEquals(textList.get(3).getText(),"Already have good base\n"
        		+ "(about 20 internal and\n"
        		+ "some external projects),\n"
        		+ "wish to get more…");   
        Assert.assertTrue(textList.get(3).isDisplayed());
	}
	
	@Test(dependsOnMethods="textTest")
	public void mainHeadersTest() {
	//9.Assert a text of the main headers
		WebElement mainTitle  = driver.findElement(By.name("main-title"));
		
		Assert.assertEquals(mainTitle.getText(),"EPAM FRAMEWORK WISHES…" );
		Assert.assertTrue(mainTitle.isDisplayed());
		
		WebElement mainText  = driver.findElement(By.name("jdi-text"));
		
		Assert.assertEquals(mainText.getText(),"LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR." );
		Assert.assertTrue(mainText.isDisplayed());
	}
	
	@Test(dependsOnMethods="mainHeadersTest")
	public void iframeTest() {
	//10.Assert that there is the iframe in the center of page
		List<WebElement> iframe = driver.findElements(By.id("jdi-frame-site"));
		
		Assert.assertFalse(iframe.isEmpty());
		Assert.assertTrue(iframe.get(0).isDisplayed());
	}
	
	@Test(dependsOnMethods="iframeTest")
	public void logoTest() {
	//11.Switch to the iframe and check that there is Epam logo in the left top corner of iframe	
		driver.switchTo().frame("jdi-frame-site");
		
		List<WebElement> logo = driver.findElements(By.className("epam-logo"));
		
		Assert.assertFalse(logo.isEmpty());
		Assert.assertTrue(logo.get(0).isDisplayed());
		
	//12.Switch to original window back
		 driver.switchTo().parentFrame();
		 driver.switchTo().defaultContent();
	}
	
	@Test(dependsOnMethods="logoTest")
	public void subHeaderTest() {
	//13.Assert a text of the sub header
		WebElement subText = driver.findElement(By.xpath("//h3[2]")).findElement(By.tagName("a"));
		
		Assert.assertTrue(subText.isDisplayed());
		Assert.assertEquals(subText.getText(), "JDI GITHUB");
		
	//14.Assert that JDI GITHUB is a link and has a proper URL
		Assert.assertEquals(subText.getTagName(),"a");
		Assert.assertEquals(subText.getAttribute("href"),"https://github.com/epam/JDI");
	}
	
	
	@Test(dependsOnMethods="subHeaderTest")
	public void leftSectionTest() {
	//15.Assert that there is Left Section
	WebElement leftSection = driver.findElement(By.id("mCSB_1"));
	
	Assert.assertTrue(leftSection.isDisplayed());
	}
	
	@Test(dependsOnMethods="leftSectionTest")
	public void footerTest() {
	//16.Assert that there is Footer
	WebElement footer = driver.findElement(By.tagName("footer"));	
	Assert.assertTrue(footer.isDisplayed());
	}
	
	@AfterTest
	public void closeSession(){
	//17.Close Browser	
     	driver.close();
	}

}

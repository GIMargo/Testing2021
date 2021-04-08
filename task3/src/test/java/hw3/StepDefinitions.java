package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.asserts.SoftAssert;

public class StepDefinitions {

	WebDriver driver;
	LoginPart loginPart;
	HeaderPart header;
	IndexPage indexPage;
	IFramePart iframe;
	SubHeaderPart subheader;
	ServiceSubcategory service;
	CheckBoxesAndRadios checkbox;
	
	@Before
    public void configTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
	
    @Given("Test site is opened")
    public void openTestSite() {
        driver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        Assert.assertEquals(driver.getCurrentUrl(),"https://jdi-testing.github.io/jdi-light/index.html");
        header = new HeaderPart(driver);
        indexPage = new IndexPage(driver);
        iframe = new IFramePart(driver);
        subheader = new SubHeaderPart(driver);
    }

    @Given("I logged in as \"ROMAN IOVLEV\"")
    public void login() {
    	loginPart = new LoginPart(driver);
    	loginPart.login("Roman", "Jdi1234");  	
    }
    
    @Then("Browser title is {string}")
    public void browserName(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }
    
    @Then("User name is {string}")
    public void userName(String username) {
        Assert.assertEquals(loginPart.getUserName(), username);
    }
    
    @Then("There are {int} items on the header section")  
    public void headerSectionSize(int count) {
    	Assert.assertEquals(header.getSize(), count);
    }
    
    @Then("These items have proper texts")  
    public void headerSectionTexts() {
    	String[] items = {"HOME","CONTACT FORM","SERVICE","METALS & COLORS"};
    	for(int i=0; i<header.getSize();i++) {
    		Assert.assertEquals(header.getName(i),items[i]);
    	}
    }
    
    @Then("These items are displayed")  
    public void headerSectionDisplayed() {
    	for(int i=0; i<header.getSize();i++) {
    		Assert.assertTrue(header.isDisplayed(i));
    	}
    }

    @Then("There are {int} images on the Index Page")
    public void indexPageImagesSize(int count) {
	    Assert.assertEquals(indexPage.getNumberOfImages(),count);
    }

    @Then("All images are displayed")
    public void indexPageImagesAreDisplayed() {
        for(int i=0; i<indexPage.getNumberOfImages();i++) {
            Assert.assertTrue(indexPage.isImageDisplayed(i));
        }
    }

    @Then("There are {int} texts on the Index Page")
    public void indexPageTextsSize(int count) {
        Assert.assertEquals(indexPage.getNumberOfTexts(),count);
    }

    @Then("All texts are displayed")
    public void indexPageTextsAreDisplayed() {
        for(int i=0; i<indexPage.getNumberOfTexts();i++) {
            Assert.assertTrue(indexPage.isTextDisplayed(i));
        }
    }

    @Then("Texts are proper")
    public void  indexPageTextsAreProper() {
        String[] items = {"To include good practices\n"
                + "and ideas from successful\n"
                + "EPAM project","To be flexible and\n"
                + "customizable","To be multiplatform","Already have good base\n"
                + "(about 20 internal and\n"
                + "some external projects),\n"
                + "wish to get more…"};
        for(int i=0; i<indexPage.getNumberOfTexts();i++) {
            Assert.assertEquals(indexPage.getText(i),items[i]);
        }
    }

    @Then("Texts of the main headers are displayed")
    public void mainHeaderTextsAreDisplayed() {
        Assert.assertTrue(header.areMainTextsDisplayed());
    }

    @Then("Texts of the main headers are proper")
    public void mainHeaderTextsAreProper() {
        String[] items ={"EPAM FRAMEWORK WISHES…",
                "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR."};
        Assert.assertEquals(header.getMainTitleText(),items[0]);
        Assert.assertEquals(header.getMainText(),items[1]);
    }

    @Then("IFrame is displayed")
    public void iframeIsDisplayed() {
	    Assert.assertFalse(iframe.isEmpty());
        Assert.assertTrue(iframe.isDisplayed());
    }

    @When("I switch to the iframe")
    public void switchToIFrame(){
	    iframe=iframe.switchToIFrame(driver);
    }

    @Then("There is epam-logo")
    public void logoExists() {
        Assert.assertFalse(iframe.isEmptyLogo());
        Assert.assertTrue(iframe.isLogoDisplayed());
    }

    @Then("I switch to original window back")
    public void switchBack() {
        iframe.comeBack(driver);
    }

    @Then("The text of the sub header is displayed")
    public void subHeaderTextIsDisplayed() {
        Assert.assertTrue(subheader.isDisplayed());
    }

    @Then("The text of the sub header is {string}")
    public void subHeaderText(String text) {
        Assert.assertEquals(subheader.getSubHeaderText(), text);
    }

    @Then("The sub header is a link")
    public void subHeaderIsALink() {
        Assert.assertTrue(subheader.isALink());
    }

    @Then("The URL is {string}")
    public void subHeaderURL(String text) {
        Assert.assertEquals(subheader.getURL(), text);
    }

    @Then("There is a Left Section")
    public void leftSection() {
        Assert.assertTrue(indexPage.isLeftSection());
    }

    @Then("There is a Footer")
    public void footer() {
        Assert.assertTrue(indexPage.isFooter());
    }

    @Then("There are {int} elements with proper texts in the header")
    public void serviceHeader(int count) {
        String[] items = {"SUPPORT","DATES","SEARCH","COMPLEX TABLE","SIMPLE TABLE","USER TABLE","TABLE WITH PAGES","DIFFERENT ELEMENTS","PERFORMANCE"};
        service = new ServiceSubcategory(driver,true);
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(service.getHeaderSize(),count);
        for(int i=0;i< service.getHeaderSize();i++){
            soft.assertEquals(service.getHeaderText(i),items[i]);
        }
        soft.assertAll();
    }

    @Then("There are {int} elements with proper texts in the left section")
    public void serviceLeftSection(int count) {
        String[] items = {"Support","Dates","Complex Table","Simple Table","Search","User Table","Table with pages","Different elements","Performance"};
        service = new ServiceSubcategory(driver,false);
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(service.getLeftSectionSize(),count);
        for(int i=0;i< service.getLeftSectionSize();i++){
            soft.assertEquals(service.getLeftSectionText(i),items[i]);
        }
        soft.assertAll();
    }

    @When("The {string} page is opened")
    public void openDifferentPage(String title) {
        service = new ServiceSubcategory(driver,true);
        service.openDifferent();
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(driver.getCurrentUrl(),"https://jdi-testing.github.io/jdi-light/different-elements.html");
        soft.assertEquals(driver.getTitle(), title);
        soft.assertAll();
        checkbox = new CheckBoxesAndRadios(driver);
    }

    @Then("There are {int} checkboxes and radios and {int} buttons and {int} dropdown")
    public void differentPageElements(int radios, int buttons,  int dropdown) {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(checkbox.numberOfCheckboxes(),radios);
        soft.assertEquals(checkbox.numberOfRadios(),radios);
        soft.assertEquals(checkbox.numberOfButtons(),buttons);
        soft.assertEquals(checkbox.numberOfDropdowns(),dropdown);
        soft.assertAll();
    }

    @Then("There are right and left sections")
    public void rightAndLeftSections() {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(checkbox.isRightSection());
        soft.assertTrue(checkbox.isLeftSection());
        soft.assertAll();
    }

    @Then("Checkboxes are selected")
    public void selectCheckboxes() {
        checkbox.selectWaterAndWind();
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(checkbox.isWaterSelected());
        soft.assertTrue(checkbox.isWindSelected());
        soft.assertAll();
    }

    @Then("Unselect checkboxes")
    public void unselectCheckboxes() {
        checkbox.selectWaterAndWind();
        SoftAssert soft = new SoftAssert();
        soft.assertFalse(checkbox.isWaterSelected());
        soft.assertFalse(checkbox.isWindSelected());
        soft.assertAll();
    }

    @Then("Log rows of checkboxes are displayed and correct")
    public void checkboxesLogs() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(checkbox.logsSize(),2);
        soft.assertTrue(checkbox.WaterLogIsDisplayed());
        soft.assertTrue(checkbox.FirstLogIsDisplayed());
        soft.assertTrue(checkbox.WaterLog("Water","true"));
        soft.assertTrue(checkbox.FirstLog("Wind","true"));
        soft.assertAll();
    }

    @Then("Log rows of unselected checkboxes are displayed and correct")
    public void unselectedCheckboxesLogs() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(checkbox.logsSize(),4);
        soft.assertTrue(checkbox.WaterLogIsDisplayed());
        soft.assertTrue(checkbox.FirstLogIsDisplayed());
        soft.assertTrue(checkbox.WaterLog("Water","false"));
        soft.assertTrue(checkbox.FirstLog("Wind","false"));
        soft.assertAll();
    }

    @Then("Selen is selected")
    public void selectSelen() {
        checkbox.selectSelen();
        Assert.assertTrue(checkbox.isSelenSelected());
    }

    @Then("Log rows of radios are displayed and correct")
    public void radiosLogs() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(checkbox.logsSize(),1);
        soft.assertTrue(checkbox.FirstLogIsDisplayed());
        soft.assertTrue(checkbox.FirstLog("Selen","metal"));
        soft.assertAll();
    }

    @Then("Yellow is selected")
    public void selectYellow() {
        checkbox.selectYellow();
        Assert.assertTrue(checkbox.isYellowSelected());
    }

    @Then("Log rows of dropdown are displayed and correct")
    public void dropdownLogs() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(checkbox.logsSize(),1);
        soft.assertTrue(checkbox.FirstLogIsDisplayed());
        soft.assertTrue(checkbox.FirstLog("Yellow","Colors"));
        soft.assertAll();
    }

    @After
    public void closeSession() {
        driver.close();
    }
}

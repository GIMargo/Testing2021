package hw3;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPart {

	@FindBy(xpath = "//*[@class='uui-navigation nav navbar-nav m-l8']/li")
	private List<WebElement> header;

	@FindBy(name = "main-title")
	private WebElement mainTitle;

	@FindBy(name = "jdi-text")
	private WebElement mainText;
	
	 public HeaderPart(WebDriver driver) {
		 PageFactory.initElements(driver, this);
     }
	 
	 public int getSize() {
		 return header.size();
	 }
	 
	 public String getName(int i) {
		 return header.get(i).getText();
	 }
	 
	 public boolean isDisplayed(int i) {
		 return header.get(i).isDisplayed();
	 }

	 public boolean areMainTextsDisplayed() {
		return mainTitle.isDisplayed()&&mainText.isDisplayed();
	 }

	 public String getMainTitleText(){
	 	return mainTitle.getText();
	 }

	 public String getMainText(){
	 	return mainText.getText();
	 }

}

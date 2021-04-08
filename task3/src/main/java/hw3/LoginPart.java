package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPart {

	@FindBy(id = "name")
    private WebElement login;
	
	@FindBy(id = "password")
    private WebElement password;
	
	@FindBy(id = "user-icon")
	private WebElement userIcon;
	
	@FindBy(id = "login-button")
	private WebElement submitButton;
	
	@FindBy(id = "user-name")
	private WebElement userName;
	
	 public LoginPart(WebDriver driver) {
		 PageFactory.initElements(driver, this);
     }
	 
	 public void login(String name, String pass) {	
		 userIcon.click();
		 login.sendKeys(name);
		 password.sendKeys(pass);
		 submitButton.click();
	 }
	 
	 public String getUserName() {
		return userName.getText();
	 }
}
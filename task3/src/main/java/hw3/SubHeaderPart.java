package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubHeaderPart {

    @FindBy(xpath = "//main//a")
    private WebElement subheader;

    public SubHeaderPart(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getSubHeaderText(){
        return subheader.getText();
    }

    public boolean isDisplayed(){
        return subheader.isDisplayed();
    }

    public boolean isALink(){
        return subheader.getTagName().equals("a");
    }

    public String getURL(){
        return subheader.getAttribute("href");
    }
}

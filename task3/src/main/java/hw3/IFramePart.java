package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IFramePart {
    @FindBy(id = "jdi-frame-site")
    private List<WebElement> iframes;

    @FindBy(id = "epam-logo")
    private List<WebElement> logo;

    public IFramePart(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isEmpty(){
        return iframes.isEmpty();
    }

    public boolean isDisplayed(){
        return iframes.get(0).isDisplayed();
    }

    public IFramePart switchToIFrame(WebDriver driver) {
        driver.switchTo().frame(iframes.get(0));
        return new IFramePart(driver);
    }

    public boolean isEmptyLogo(){
        return logo.isEmpty();
    }

    public boolean isLogoDisplayed(){
        return logo.get(0).isDisplayed();
    }

    public void comeBack(WebDriver driver){
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }
}

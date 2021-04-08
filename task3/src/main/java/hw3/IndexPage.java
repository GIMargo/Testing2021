package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IndexPage {

    @FindBy(xpath = "//*[contains(@class,'benefit-icon')]")
    private List<WebElement> images;

    @FindBy(xpath = "//*[contains(@class,'benefit-txt')]")
    private List<WebElement> texts;

    @FindBy(id = "mCSB_1")
    private WebElement leftSection;

    @FindBy(tagName = "footer")
    private WebElement footer;

    public IndexPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfImages() {
        return images.size();
    }

    public int getNumberOfTexts() {
        return texts.size();
    }

    public boolean isImageDisplayed(int i) {
        return images.get(i).isDisplayed();
    }

    public boolean isTextDisplayed(int i) {
        return texts.get(i).isDisplayed();
    }

    public String getText(int i) {
        return texts.get(i).getText();
    }

    public boolean isLeftSection(){
        return leftSection.isDisplayed();
    }

    public boolean isFooter(){
        return footer.isDisplayed();
    }


}

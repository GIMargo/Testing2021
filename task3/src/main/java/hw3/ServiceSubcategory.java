package hw3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ServiceSubcategory {

    @FindBy(className = "dropdown-toggle")
    private WebElement toggle1;

    @FindBy(className = "menu-title")
    private WebElement toggle2;

    @FindBy(xpath = "//*[@class='dropdown-menu']/li")
    private List<WebElement> header;

    @FindBy(xpath = "//*[@class='sub']/li")
    private List<WebElement> leftSection;

    public ServiceSubcategory(WebDriver driver,boolean header) {
        PageFactory.initElements(driver, this);
        if(header){
            toggle1.click();
        }else{
            toggle2.click();
        }
    }

    public int getHeaderSize(){
        return header.size();
    }

    public int getLeftSectionSize(){
        return leftSection.size();
    }

    public String getHeaderText(int i){
        return header.get(i).getText();
    }

    public String getLeftSectionText(int i){
        return leftSection.get(i).getText();
    }

    public void openDifferent(){
        header.get(7).click();
    }

}

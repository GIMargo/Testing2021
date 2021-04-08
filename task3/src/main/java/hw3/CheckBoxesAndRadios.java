package hw3;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckBoxesAndRadios {

    @FindBy(xpath = "//*[@type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//*[@type='radio']")
    private List<WebElement> radios;

    @FindBy(tagName = "select")
    private List<WebElement> dropdown;

    @FindBy(xpath = "//*[@class='uui-button']")
    private List<WebElement> buttons;

    @FindBy(xpath = "//*[@name='log-sidebar']")
    private WebElement right;

    @FindBy(id = "mCSB_1")
    private WebElement left;

    @FindBy(xpath = "//*[@class='panel-body-list logs']/li")
    private List<WebElement> logs;

    @FindBy(xpath = "//select/option[last()]")
    private WebElement yellow;

    public CheckBoxesAndRadios(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int numberOfCheckboxes(){
        return checkboxes.size();
    }

    public int numberOfRadios(){
        return radios.size();
    }

    public int numberOfButtons(){
        return buttons.size();
    }

    public int numberOfDropdowns(){
        return dropdown.size();
    }

    public boolean isRightSection(){
        return right.isDisplayed();
    }

    public boolean isLeftSection(){
        return left.isDisplayed();
    }

    public void selectWaterAndWind(){
        checkboxes.get(0).click();
        checkboxes.get(2).click();
    }

    public boolean isWaterSelected(){
        return checkboxes.get(0).isSelected();
    }

    public boolean isWindSelected(){
        return checkboxes.get(2).isSelected();
    }

    public int logsSize(){
        return logs.size();
    }

    public boolean WaterLog(String water, String state){
        String waterlog = logs.get(1).getText();
        return waterlog.contains(water)&&(waterlog.contains(state));
    }

    public boolean FirstLog(String text, String state){
        String log = logs.get(0).getText();
        return log.contains(text)&&(log.contains(state));
    }

    public boolean WaterLogIsDisplayed(){
        return logs.get(1).isDisplayed();
    }

    public boolean FirstLogIsDisplayed(){
        return logs.get(0).isDisplayed();
    }

    public void selectSelen(){
        radios.get(3).click();
    }

    public void selectYellow(){
        dropdown.get(0).click();
        yellow.click();
    }

    public boolean isSelenSelected(){
        return radios.get(3).isSelected();
    }

    public boolean isYellowSelected(){
        return yellow.isSelected();
    }


}

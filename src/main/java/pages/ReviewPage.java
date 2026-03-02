package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DateUtil;

import java.time.Duration;

public class ReviewPage {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }
    private By btnReview = By.id("reviewButton");
    private By inputNote = By.id("noteModalInput");
    private By btnConfirm  = By.id("submitEvaluation");

    public void review(){
        wait.until(ExpectedConditions.elementToBeClickable(btnReview)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inputNote)).sendKeys("Auto ghi chu");
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
    }


}

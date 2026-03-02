package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    private By btnOptionOtp = By.xpath("//p[text()='Sử dụng mật khẩu một lần (OTP)']");
    private By btnNext = By.xpath("//button[text()='TIẾP THEO']");
    private By inputPass = By.xpath("//input[@type=\"password\"]");
    private By btnLogin = By.xpath("//button[text()='ĐĂNG NHẬP']");

    public void login(String username, String password) {
        WebElement elementOptionOtp = wait.until(ExpectedConditions.elementToBeClickable(btnOptionOtp));
        elementOptionOtp.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys(username);
        WebElement elementNext = wait.until(ExpectedConditions.elementToBeClickable(btnNext));
        elementNext.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(inputPass)).sendKeys(password);
        WebElement elementLogin = wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        elementLogin.click();
    }
}

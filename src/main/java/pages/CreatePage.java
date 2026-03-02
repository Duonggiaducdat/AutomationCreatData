package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DateUtil;

import java.time.Duration;
import java.time.LocalDate;

public class CreatePage {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    public CreatePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }
    private String name = "Qc test " + DateUtil.getTimeForFileName();

    private By labelInfor = By.xpath("//h3[text() = 'Thông tin cơ hội']");
    private By labelInforCus = By.xpath("//h3[text() = 'Thông tin khách hàng']");
    private By chooseServiceGroup = By.xpath("//input[@placeholder='Chọn nhóm dịch vụ']");
    private By chooseServiceType = By.xpath("//input[@placeholder='Chọn loại dịch vụ']");
    private By chooseServiceGroup2 = By.xpath("//div[@class='field-wrapper-content']//descendant::input[@placeholder='Chọn nhóm dịch vụ']");
    private By chooseServiceGroup3 = By.xpath("//div[@class='field-wrapper-content']//descendant::input[@aria-owns='serviceGroup_3_listbox']");
    private By chooseServiceGroup4 = By.xpath("//div[@class='field-wrapper-content']//descendant::input[@aria-owns='serviceGroup_4_listbox']");

    private By chooseServiceType2 = By.xpath("//div[@class='field-wrapper-content']//descendant::input[@placeholder='Chọn loại dịch vụ']");
    private By chooseServiceType3 = By.xpath("//div[@class='field-wrapper-content']//descendant::input[@aria-owns='serviceType_3_listbox']");
    private By chooseServiceType4 = By.xpath("//div[@class='field-wrapper-content']//descendant::input[@aria-owns='serviceType_4_listbox']");
    private By inputDescription = By.xpath("//textarea[@placeholder='Mô tả cơ hội']");

    private String labelOptionXpath = "//li[text()='%s']";
    public By getLabelOption(String value) {
        return By.xpath(String.format(labelOptionXpath, value));
    }
    private By calender = By.xpath("//span[@aria-controls='txtEndDate_dateview']");
    private By toDay = By.xpath("//td[@class='k-today k-state-focused']//a");
    private By btnNext = By.id("btnInsOpp_Tao");
    private By inputSearch = By.xpath("//input[@placeholder='Tìm kiếm khách hàng bằng tên khách hàng, mã khách hàng hoặc mã số thuế, CCCD']");
    private By btnChoose = By.xpath("//button[text() = 'Chọn']");
    private By chooseReviewOptions = By.xpath("//span[text() = 'Chọn người đánh giá']//parent::div");
    private By chooseProveOptions = By.xpath("//span[text() = 'Chọn người phê duyệt']//parent::div[@role='combobox']");
    private By chooseReview = By.xpath("//li[text() = 'Dungntm10 - Dungntm10@fpt.com - DungNTM10']");
    private By chooseProve = By.xpath("//ul[@id='approvalByChoose_listbox']//li[text() = 'Huyennhk - HuyenNHK']");
    private By btnAdd= By.xpath("//span[text()='Thêm dịch vụ']");

    private By scrollProveOptions = By.xpath("//li[text() = 'Huyennhk - HuyenNHK']//parent::ul[@id='approvalByChoose_listbox']//parent::div");
    public void createStep1 () throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelInfor));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtNameOpp"))).sendKeys(name);
        wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceGroup)).sendKeys("Trung tâm dữ liệu");
        wait.until(driver ->
                driver.findElement(chooseServiceType)
                        .getAttribute("aria-disabled")
                        .equals("false")
        );
        wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceType)).sendKeys("Dedicated");
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        getLabelOption("Dedicated")
                )
        ).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(btnAdd)).click();
        WebElement optionGroup = wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceGroup2));
        optionGroup.click();
        optionGroup.sendKeys("Trung tâm dữ liệu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelInfor)).click();
        WebElement optionType = wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceType2));
        wait.until(driver ->
                optionType.getAttribute("aria-disabled").equals("false")
        );

        optionType.click();
        optionType.sendKeys("Colocation");
        wait.until(ExpectedConditions.presenceOfElementLocated(btnAdd)).click();
        WebElement optionGroup3 = wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceGroup3));
        optionGroup3.click();
        optionGroup3.sendKeys("Trung tâm dữ liệu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelInfor)).click();
        WebElement optionType3 = wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceType3));
        wait.until(driver ->
                optionType3.getAttribute("aria-disabled").equals("false")
        );

        optionType3.click();
        optionType3.sendKeys("Rack");
        wait.until(ExpectedConditions.presenceOfElementLocated(btnAdd)).click();
        WebElement optionGroup4 = wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceGroup4));
        optionGroup4.click();
        optionGroup4.sendKeys("Trung tâm dữ liệu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelInfor)).click();
        WebElement optionType4 = wait.until(ExpectedConditions.presenceOfElementLocated(chooseServiceType4));
        wait.until(driver ->
                optionType4.getAttribute("aria-disabled").equals("false")
        );

        optionType4.click();
        optionType4.sendKeys("X-Connect");

        wait.until(ExpectedConditions.presenceOfElementLocated(inputDescription)).sendKeys("Qc test");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtExpectedRevenue"))).sendKeys("5000000");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtSuccessRate")));
        element.click();
        element.sendKeys("80");
        wait.until(ExpectedConditions.presenceOfElementLocated(calender)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(toDay)).click();
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(btnNext)).click();
    }
    public void createStep2 () throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(inputSearch)).sendKeys("0976987789");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-search"))).click();
        driver.findElement(btnChoose).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(labelInforCus));
        Thread.sleep(1000);
        WebElement elementNext = wait.until(ExpectedConditions.elementToBeClickable(btnNext));
       ((JavascriptExecutor) driver).executeScript(
                 "window.scrollTo(0, arguments[0].offsetTop - 100);",
                elementNext
        );
        Thread.sleep(1000);
        elementNext.click();
    }
    public void hideChatbot() {
        ((JavascriptExecutor) driver).executeScript(
                "let btn = document.querySelector('.chatbot-btn');" +
                        "if(btn) btn.style.display='none';"
        );
    }
    public void createStep3 () throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(chooseReviewOptions)).click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseReview)).click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseProveOptions)).click();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop += 200;",
                driver.findElement(scrollProveOptions)
        );
        System.out.println("1");
        wait.until(ExpectedConditions.elementToBeClickable(chooseProve)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(btnNext)).click();
        wait.until(ExpectedConditions.elementToBeClickable(btnNext)).click();
    }
}

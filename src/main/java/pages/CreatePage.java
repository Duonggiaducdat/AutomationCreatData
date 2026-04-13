package pages;

import base.BasePage;
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
import java.util.List;

public class CreatePage extends BasePage {
    Actions actions;
    WebDriverWait wait;
    public CreatePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
    }
//    private String name = "Dat test >= 1 tu " + DateUtil.getTimeForFileName();

    private By labelInfor = By.xpath("//h3[text() = 'Thông tin cơ hội']");
    By chooseServiceGroup(int index){
        return By.xpath(String.format("//input[@aria-owns='serviceGroup_%d_listbox']",index));
    }

    By chooseServiceType(int index){
        return By.xpath(String.format("//input[@aria-owns='serviceType_%d_listbox']",index));
    }
    private By inputDescription = By.xpath("//textarea[@placeholder='Mô tả cơ hội']");

    private String labelOptionXpath = "//li[text()='%s']";
    public By getLabelOption(String value) {
        return By.xpath(String.format(labelOptionXpath, value));
    }
    private By inputCalender = By.xpath("//input[@id='txtEndDate']");
    private By toDay = By.xpath("//td[@class='k-today k-state-focused']//a");
    private By btnNext = By.id("btnInsOpp_Tao");
    private By inputSearch = By.xpath("//input[@placeholder='Tìm kiếm khách hàng bằng tên khách hàng, mã khách hàng hoặc mã số thuế, CCCD']");
    private By btnChoose = By.xpath("//tr[1]//button[text() = 'Chọn']");
    private By chooseReviewOptions = By.xpath("//span[text() = 'Chọn người đánh giá']//parent::div");
    private By chooseProveOptions = By.xpath("//span[text() = 'Chọn người phê duyệt']//parent::div[@role='combobox']//input");
    private By chooseReview = By.xpath("//li[text() = 'Dungntm10 - Dungntm10@fpt.com - DungNTM10']");
    By chooseReview(String index){
        return By.xpath(String.format("//li[contains(text(),'%s')]",index));
    }
    private By chooseProve = By.xpath("//ul[@id='approvalByChoose_listbox']//li[text() = 'Huyennhk - HuyenNHK']");
    By chooseProve (String index){
        return By.xpath(String.format("//ul[@id='approvalByChoose_listbox']//li[contains(text(),'%s')]",index));
    }
    private By btnAdd= By.xpath("//span[text()='Thêm dịch vụ']");

    private By scrollProveOptions = By.xpath("//li[text() = 'Huyennhk - HuyenNHK']//parent::ul[@id='approvalByChoose_listbox']//parent::div");
    private By inputAbility = By.xpath("//input[@placeholder='%']");
    private By rack = By.xpath("//span[@aria-owns='numberRack_listbox']");
    private By moreRack = By.xpath("//li[text()='Nhiều hơn hoặc bằng 1 tủ']");
    public void createStep1 (String groupService, List<String> serviceTypes, String options, String name) {
        sendKeys(By.id("txtNameOpp"),name + DateUtil.getTimeForFileName());
        System.out.println(serviceTypes.size());
        for(int index = 0; index < serviceTypes.size(); index++) {
            addService(index + 1, groupService, serviceTypes.get(index));

            if (index < serviceTypes.size() - 2) {
                click(btnAdd);
                waitForPageLoaded();
            }
        }
        if("Trung tâm dữ liệu".equalsIgnoreCase(groupService) && "Nhiều hơn hoặc bằng 1 tủ".equalsIgnoreCase(options)){
            scrollToBottom();
            sleep(2);
            click(rack);
            click(moreRack);
        }
        sendKeys(inputDescription,"QC test");
        sendKeys(By.id("txtExpectedRevenue"),"5000000");
        clickAndSendKeys(inputAbility,"90");
        sendKeys(inputCalender,DateUtil.getToday());
        scrollToBottom();
        sleep(2);
        click(btnNext);
    }
    public void createStep2 (String customerName) {
        sendKeys(inputSearch,customerName);
        click(By.id("btn-search"));
        click(btnChoose);
        sleep(1);
        waitForPageLoaded();
        scrollToBottom();
        sleep(1);
        click(btnNext);
    }

    public void createStep3 (String truongPhong, String banGD) {
        click(chooseReviewOptions);
        click(chooseReview(truongPhong));
        click(chooseProveOptions);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop += 200;",
                driver.findElement(chooseProve(banGD))
        );
        sleep(2);
        click(chooseProve(banGD));
        sleep(2);
        clickBody();
        click(btnNext);
        waitForPageLoaded();
    }

    public void addService(int index, String group, String type){

        sendKeys(chooseServiceGroup(index), group);

        clickBody();
        click(labelInfor);

        waitForAttributeToBe(chooseServiceType(index),"aria-disabled","false");

        sendKeys(chooseServiceType(index), type);

        clickBody();

    }
}

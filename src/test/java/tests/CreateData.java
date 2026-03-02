package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.CreatePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ReviewPage;

public class CreateData extends BaseTest {
    private By maCH = By.xpath("//tbody[@role=\"rowgroup\"]//tr[1]//descendant::a[contains(text(),'CH0008')]");
    LoginPage loginPage;
    CreatePage createPage;
    LogoutPage logoutPage;
    ReviewPage reviewPage;
    @Test
    public void createData() throws InterruptedException {
        loginPage = new LoginPage(driver);
        createPage = new CreatePage(driver);
        logoutPage =new LogoutPage(driver);
        reviewPage =new ReviewPage(driver);
        loginPage.login("thitlh@fpt.com", "ISC22QC");
        createPage.hideChatbot();
        createPage.createStep1();
        createPage.createStep2();
        createPage.createStep3();
        Thread.sleep(3000);
        String href = driver.findElement(maCH).getAttribute("href");
        System.out.println(href);
        driver.get(href);
        logoutPage.Logout();
        loginPage.login("chuyennt5@fpt.com", "ISC22QC");
        driver.get(href);
        Thread.sleep(3000);
        reviewPage.review();
        Thread.sleep(15000);



    }

}

package tests;

import base.BaseTest;
import models.ServiceGroup;
import net.bytebuddy.jar.asm.TypeReference;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import utils.DateUtil;
import utils.ExcelUtil;
import utils.JsonUtil;

import java.util.*;

public class CreateData extends BaseTest {
    private By maCH = By.xpath("//tbody[@role='rowgroup']//tr[1]//descendant::a[contains(text(),'CH0008')]");
    private By maCH1 = By.xpath("//a[contains(text(),'CH0008')]");
    LoginPage loginPage;
    CreatePage createPage;
    LogoutPage logoutPage;
    ReviewPage reviewPage;
    BaoGia baoGia;
    CreateAppendixContract createAppendixContract;
    RequestIDC requestIDC;
    DetailPage detailPage;
    RequestCM requestCM;
    @Parameters({"serviceGroup","customerName", "options", "name", "optionsService","userNameSale","passSale","truongPhong","banGiamDoc"})
    @Test
    public void testCreate(String serviceGroup, String customerName, String options, String name, String optionsService, String userNameSale, String passSale, String truongPhong, String banGiamDoc){
        ServiceGroup data = JsonUtil.loadJsonData("Services.json", ServiceGroup.class);

        List<String> services = data.getGroup()
                .get(serviceGroup)
                .getServices();
        System.out.println(services);
        loginPage = new LoginPage(driver);
        createPage = new CreatePage(driver);
        logoutPage = new LogoutPage(driver);
        reviewPage = new ReviewPage(driver);
        baoGia = new BaoGia(driver);
        createAppendixContract = new CreateAppendixContract(driver);
        requestIDC = new RequestIDC(driver);
        detailPage = new DetailPage(driver);
        requestCM = new RequestCM(driver);
        loginPage.login(userNameSale,passSale);
        List<String> selectedServices;
        selectedServices = services;
//        // ===== 3. CREATE ORDER =====
        createPage.createStep1(serviceGroup, selectedServices, options, name);
        createPage.createStep2(customerName);
        createPage.createStep3(truongPhong,banGiamDoc);

        String href = detailPage.getHref(maCH);
        System.out.println("Link CH: " + href);

        driver.get(href);
        logoutPage.Logout();

//         ===== 4. REVIEW =====
        loginPage.login("chuyennt5@fpt.com", "ISC22QC");
        driver.get(href) ;
        reviewPage.review();

        String href1 = detailPage.getHref(maCH1);
        driver.get(href1);

        // ===== 5. BÁO GIÁ =====
        baoGia.addQuote();
        if (Objects.isNull(optionsService) || optionsService.trim().isEmpty()) {
            selectedServices = services; // FIX ở đây
        } else {
            selectedServices = new ArrayList<>(Arrays.asList(optionsService.split("\\s*,\\s*")));
        }
        // ===== 6. LOOP SERVICES =====
        for (String service : selectedServices) {
            if (service.equals("Rack") && "Nhiều hơn hoặc bằng 1 tủ".equals(options)) {
                System.out.println(driver.getCurrentUrl() + href1);
                driver.get(href1);
                String maCH= detailPage.getMaCH();
                detailPage.addWorkBookRack();
                requestIDC.createBookRack("FORNIX - HN02");
                detailPage.step2BookRack();
                requestIDC.sendIDC(maCH);
                continue;
            }
            driver.get(href1);
            System.out.println(serviceGroup +" - " + service);
            createAppendixContract.request(serviceGroup+" - " + service);
            createAppendixContract.createStep1();
            createAppendixContract.createStep2();
            requestIDC.createGeneralInformation();
            requestIDC.createRequest();
            createAppendixContract.createStep3();
            detailPage.approvalService(serviceGroup+" - " + service);
            driver.get(href1);
            driver.navigate().refresh();
            System.out.println("Processing: " + service);
            String maYC = detailPage.getMaYC(serviceGroup+" - " + service);
            requestCM.request(maYC);
        }
        // ===== 7. EXPORT =====
        driver.get(href1);
        detailPage.clickTabRegist();
        ExcelUtil.exportTable(driver , "ThongtinHD", detailPage.getMaCH());
    }
//    @Parameters({"serviceGroup","customerName", "options", "name", "optionsService","userNameSale","passSale","truongPhong","banGiamDoc"})
//    @Test
//    public void test(String serviceGroup, String customerName, String options, String name, String optionsService, String userNameSale, String passSale, String truongPhong, String banGiamDoc){
//        ServiceGroup data = JsonUtil.loadJsonData("Services.json", ServiceGroup.class);
//
//        List<String> services = data.getGroup()
//                .get(serviceGroup)
//                .getServices();
//        System.out.println(services);
//        loginPage = new LoginPage(driver);
//        createPage = new CreatePage(driver);
//        logoutPage = new LogoutPage(driver);
//        reviewPage = new ReviewPage(driver);
//        baoGia = new BaoGia(driver);
//        createAppendixContract = new CreateAppendixContract(driver);
//        requestIDC = new RequestIDC(driver);
//        detailPage = new DetailPage(driver);
//        requestCM = new RequestCM(driver);
////        loginPage.login(userNameSale,passSale);
////        List<String> selectedServices;
////        selectedServices = services;
////        // ===== 3. CREATE ORDER =====
////        createPage.createStep1(serviceGroup, selectedServices, options, name);
////        createPage.createStep2(customerName);
////        createPage.createStep3(truongPhong,banGiamDoc);
////
////        String href = detailPage.getHref(maCH);
////        System.out.println("Link CH: " + href);
////
////        driver.get(href);
////        logoutPage.Logout();
//
////         ===== 4. REVIEW =====
//        loginPage.login("chuyennt5@fpt.com", "ISC22QC");
////        driver.get(href);
////        reviewPage.review();
////
////        String href1 = detailPage.getHref(maCH1);
////        driver.get(href1);
////
////        // ===== 5. BÁO GIÁ =====
////        baoGia.addQuote();
////        if (Objects.isNull(optionsService) || optionsService.trim().isEmpty()) {
////            selectedServices = services; // FIX ở đây
////        } else {
////            selectedServices = new ArrayList<>(Arrays.asList(optionsService.split("\\s*,\\s*")));
////        }
////        // ===== 6. LOOP SERVICES =====
////        for (String service : selectedServices) {
////            if (service.equals("Rack") && "Nhiều hơn hoặc bằng 1 tủ".equals(options)) {
////                System.out.println(driver.getCurrentUrl() + href1);
////                driver.get("https://ftms-stag.fpt.net/idc/V3/BMS/Add?ServiceType=8003&Type=KS&RackType=1&key=DFSLTLVNFOGHYIOQCGDPOGFNSSRCLTFWENXEOPKMBIZUTKIATMBJFBTAJWYXIDHXDAFBDGLTNGFYOENYGKRNZPDKVTJYGPGEQRPY");
////                String maCH= detailPage.getMaCH();
////                detailPage.addWorkBookRack();
//        driver.get("https://ftms-stag.fpt.net/idc/V3/BMS/Add?ServiceType=8003&Type=KS&RackType=1&key=DFSLTLVNFOGHYIOQCGDPOGFNSSRCLTFWENXEOPKMBIZUTKIATMBJFBTAJWYXIDHXDAFBDGLTNGFYOENYGKRNZPDKVTJYGPGEQRPY");
//                requestIDC.createBookRack("FORNIX - HN02");
//                detailPage.step2BookRack();
//                requestIDC.sendIDC("CH00081978");
////                continue;
////            }
////            driver.get(href1);
////            System.out.println(serviceGroup +" - " + service);
////            createAppendixContract.request(serviceGroup+" - " + service);
////            createAppendixContract.createStep1();
////            createAppendixContract.createStep2();
////            requestIDC.createGeneralInformation();
////            requestIDC.createRequest();
////            createAppendixContract.createStep3();
////            detailPage.approvalService(serviceGroup+" - " + service);
////            driver.get(href1);
////            driver.navigate().refresh();
////            System.out.println("Processing: " + service);
////            String maYC = detailPage.getMaYC(serviceGroup+" - " + service);
////            requestCM.request(maYC);
////        }
////        // ===== 7. EXPORT =====
////        driver.get(href1);
////        detailPage.clickTabRegist();
////        ExcelUtil.exportTable(driver , "ThongtinHD", detailPage.getMaCH());
//    }
}

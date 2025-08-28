package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import listeners.TestListener;
import pages.LoginPage;
import utils.ExcelUtils;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @DataProvider(name = "excelData")
    public Object[][] getExcelData() {
        String filePath = "src/test/resources/testdata/SauceDemoTestData.xlsx";
        return ExcelUtils.getExcelData(filePath,"LoginData");
    }

    @Test(dataProvider = "excelData")
    public void testLoginScenariosFromExcel(String username, String password, boolean isSuccess) {
        loginPage = new LoginPage(driver);

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        if(isSuccess) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),"Expected to land on inventory page for user: " + username);
        } else {
            // Validate error message
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertTrue(!errorMessage.isEmpty(),"Expected to see error message for user: " + username);
        }
    }
}

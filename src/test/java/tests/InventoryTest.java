package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import listeners.TestListener;
import pages.LoginPage;
import pages.InventoryPage;
import utils.ExcelUtils;

import java.util.List;

@Listeners(TestListener.class)
public class InventoryTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @DataProvider(name = "excelData")
    public Object[][] getExcelData(){
        String filePath = "src/test/resources/testdata/SauceDemoTestData.xlsx";
        return ExcelUtils.getExcelData(filePath,"Inventory");
    }

    @Test(dataProvider = "excelData")
    public void testVerifyInventoryList(String itemName, String price, String description) {
        inventoryPage = new InventoryPage(driver);
        List<String> itemsInInventory = inventoryPage.getInventoryItems(); //gets items in webpage
        Assert.assertTrue(itemsInInventory.contains(itemName), "Expected item not found in inventory: " + itemName);
    }
}

//Currently logging in everytime it checks the next row. Can still be optimized.
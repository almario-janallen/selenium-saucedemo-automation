package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import listeners.TestListener;
import pages.InventoryItemPage;
import pages.LoginPage;
import pages.InventoryPage;
import utils.ExcelUtils;

import java.text.DecimalFormat;

@Listeners(TestListener.class)
public class InventoryItemTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    InventoryItemPage inventoryItemPage;

    @DataProvider(name="excelData")
    public Object[][] getExcelData(){
        String filePath = "src/test/resources/testdata/SauceDemoTestData.xlsx";
        return ExcelUtils.getExcelData(filePath,"Inventory");
    }

    @Test(dataProvider = "excelData")
    public void testVerifyItemDetails(String inventoryItemName, String priceStr, String description){

        inventoryPage = new InventoryPage(driver);
        inventoryPage.goToProduct(inventoryItemName);
        inventoryItemPage = new InventoryItemPage(driver);

        // ✅ Verify Name
        Assert.assertEquals(inventoryItemPage.getInventoryItemName(), inventoryItemName,"Item name mismatch!");

        // ✅ Verify Description
        Assert.assertEquals(inventoryItemPage.getInventoryItemDescription(),description,"Item description mismatch!");

        // ✅ Verify Price (Excel is numeric, webpage has "$xx.xx")
        double price = Double.parseDouble(priceStr);
        String expectedPrice = new DecimalFormat("$#.00").format(price);  // e.g. $29.99
        Assert.assertEquals(inventoryItemPage.getInventoryItemPrice(),expectedPrice,"Item price mismatch!");

        // Go back for next loop
        inventoryItemPage.backToProducts();
    }
}

package NithinThomas;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseClass;
import project.pageobjects.CartPage;
import project.pageobjects.CheckOutPage;
import project.pageobjects.HomePage;

public class TestWithExcel extends BaseClass {

	DataFormatter formatter = new DataFormatter();

	String ProductName = "ZARA COAT 3";

	@Test(dataProvider = "getDataFromExcel")
	public void SubmitOrder(String userName, String password) throws IOException {

		HomePage homePage = loginPage.login(userName, password);
		homePage.addProductToCart("ZARA COAT 3");
		CartPage cartPage = homePage.goToCartPage();
		Assert.assertTrue(cartPage.verifyCartDisplay(ProductName));
		CheckOutPage checkoutpage = cartPage.CheckOut();
		checkoutpage.sentCountryName();
		// checkoutpage.submitOrder();
	}

	@DataProvider(name = "getDataFromExcel")
	public Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/Data/excelLoginData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		XSSFRow row = sheet.getRow(0);
		System.out.println(row);
		int colCount = row.getLastCellNum();
		System.out.println(colCount);
		Object[][] data = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
				System.out.println(row.getCell(j));
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		System.out.println(data);
		wb.close();
		return data;
	}
}

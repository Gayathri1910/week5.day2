package assignment10.day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PoiLead extends BaseLead {
		@Test(dataProvider = "POI", enabled = true, priority = 1)
		public void create(String cmpny, String fname, String lname, String phn) {

			driver.findElement(By.linkText("Create Lead")).click();
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys(cmpny);
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fname);
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lname);
			driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phn);
			driver.findElement(By.name("submitButton")).click();
		}

		@DataProvider
		public static String[][] POI() throws IOException {
			// TODO Auto-generated method stub
			XSSFWorkbook wb1 = new XSSFWorkbook("./data/CreateLead.xlsx");
			XSSFSheet ws1 = wb1.getSheet("create");
			//get row count
			int rc = ws1.getLastRowNum();
			//get column count
			int cc = ws1.getRow(0).getLastCellNum();
			String[][] D = new String[rc][cc];
			for (int i = 1; i <= rc; i++) {
				for (int j = 0; j < cc; j++) {
					String data = ws1.getRow(i).getCell(j).getStringCellValue();
					// System.out.println(data);
					D[i - 1][j] = data;
				}
			}
			wb1.close();
			System.out.println("Create inputs are : " + D);
			return D;
		}

		@Test(dataProvider = "poiDelete", enabled = true, priority = 5)
		public void delete(String phn) throws InterruptedException {
			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//span[text()='Phone']")).click();
			driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phn);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(2000);
			String leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"))
					.getText();
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			driver.findElement(By.linkText("Delete")).click();
			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			String text = driver.findElement(By.className("x-paging-info")).getText();
			if (text.equals("No records to display")) {
				System.out.println("Text matched");
			} else {
				System.out.println("Text not matched");
			}
		}

		@DataProvider
		public static String[][] poiDelete() throws IOException {
			XSSFWorkbook wb2 = new XSSFWorkbook("./data/CreateLead.xlsx");
			XSSFSheet s = wb2.getSheet("delete");
			int RS = s.getLastRowNum();
			int CS = s.getRow(0).getLastCellNum();
			String[][] data = new String[RS][CS];
			for (int i = 1; i <= RS; i++) {
				for (int j = 0; j < CS; j++) {
					String val = s.getRow(i).getCell(j).getStringCellValue();
					data[i - 1][j] = val;
				}
			}
			wb2.close();
			System.out.println("Delete data passed are: " + data);
			return data;
		}

		@Test(dataProvider = "duplicatedata", enabled = true, priority = 3)
		public void duplicate(String phn) throws InterruptedException {
			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//span[text()='Phone']")).click();
			driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phn);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			driver.findElement(By.linkText("Duplicate Lead")).click();
			driver.findElement(By.name("submitButton")).click();
		}

		@DataProvider
		public String[][] duplicatedata() throws IOException {
			XSSFWorkbook WB = new XSSFWorkbook("./data/CreateLead.xlsx");
			XSSFSheet WS = WB.getSheet("delete");
			int Rcount = WS.getLastRowNum();
			int Ccount = WS.getRow(0).getLastCellNum();
			String[][] val = new String[Rcount][Ccount];
			for (int i = 1; i <= Rcount; i++) {
				for (int j = 0; j < Ccount; j++) {
					String contains = WS.getRow(i).getCell(j).getStringCellValue();
					val[i - 1][j] = contains;

				}

			}
			WB.close();
			return val;

		}

		@Test(dataProvider = "editdata", enabled = true,priority = 2)
		public void edit(String phn, String cmpny) throws InterruptedException {
			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//span[text()='Phone']")).click();
			driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phn);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			driver.findElement(By.linkText("Edit")).click();
			driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(cmpny);
			driver.findElement(By.name("submitButton")).click();
		}

		@DataProvider
		public String[][] editdata() throws IOException {
			XSSFWorkbook WB=new XSSFWorkbook("./data/CreateLead.xlsx");
			XSSFSheet WS=WB.getSheet("edit");
			int rows= WS.getLastRowNum();
			int col= WS.getRow(0).getLastCellNum();
			String[][] data=new String[rows][col];
			for (int i = 1; i <=rows; i++) {
				for (int j = 0; j < col; j++) {
					String cv=WS.getRow(i).getCell(j).getStringCellValue();
					data[i-1][j]=cv;
				}
			}
			WB.close();
			return data;
		}

		@Test(dataProvider = "mergedata", enabled = true,priority = 4)
		public void merge(String fname, String name) throws InterruptedException {
			driver.findElement(By.linkText("Merge Leads")).click();
			driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
			Set<String> allWindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			driver.switchTo().window(allhandles.get(1));
			driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(1000);
			String leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			driver.switchTo().window(allhandles.get(0));
			driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
			Set<String> allWindows2 = driver.getWindowHandles();
			List<String> allhandles2 = new ArrayList<String>(allWindows2);
			driver.switchTo().window(allhandles2.get(1));
			driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(name);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			driver.switchTo().window(allhandles2.get(0));
			driver.findElement(By.xpath("//a[text()='Merge']")).click();
			driver.switchTo().alert().accept();

			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			String text = driver.findElement(By.className("x-paging-info")).getText();
			if (text.equals("No records to display")) {
				System.out.println("Text matched");
			} else {
				System.out.println("Text not matched");
			}
		}

		@DataProvider
		public String[][] mergedata() throws IOException {
			XSSFWorkbook wb=new XSSFWorkbook("./data/CreateLead.xlsx");
			XSSFSheet ws=wb.getSheet("merge");
			int rs = ws.getLastRowNum();
			short cc = ws.getRow(0).getLastCellNum();
			String[][] data=new String[rs][cc];
			for (int i = 1; i <=rs; i++) {
				for (int j = 0; j < cc; j++) {
					String val=ws.getRow(i).getCell(j).getStringCellValue();
					data[i-1][j]=val;
				}
			}
			wb.close();
			return data;
		}

}

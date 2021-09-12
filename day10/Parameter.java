package assignment10.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Parameter extends BaseLead{
	
	
	@Test(dataProvider="createdata",priority=1)
	public void create(String cmpny, String fname, String lname, String phn) {
		
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(cmpny);
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fname);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lname);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phn);
		driver.findElement(By.name("submitButton")).click();
}
	@DataProvider
	public String[][] createdata(){
		String[][] data=new String[2][4];
		data[0][0]="TCS";
		data[0][1]="Pujitha";
		data[0][2]="J";
		data[0][3]="123";
		
		data[1][0]="CTS";
		data[1][1]="Siri";
		data[1][2]="Ch";
		data[1][3]="456";
		return data;
		
	}

	@Test(dataProvider="editdata",priority=2)
	public void edit(String phn,String cmpny) throws InterruptedException {
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
	public String[][] editdata() {
		String[][] data= new String[2][2];
		data[0][0]="99";
		data[0][1]="TCS";
		data[1][0]="98";
		data[1][1]="CTS";
		return data;
	}
	@Test(dataProvider="deletedata",priority=5
			)
	public void delete(String phn) throws InterruptedException {
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phn);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		String leadID = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
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
	public String[] deletedata() {
		String[] data=new String[3];
		data[0]="99";
		data[1]="91";
		data[2]="98";
		return data;
	}
	@Test(dataProvider="mergedata",priority=3)
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
	public String[][] mergedata() {
		String[][] data=new String[2][2];
		data[0][0]="a";
		data[0][1]="b";
		data[1][0]="b";
		data[1][1]="a";
		return data;
	}
	@Test(dataProvider="duplicatedata",priority=4)
	public void duplicate(String phn) throws InterruptedException {
			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//span[text()='Phone']")).click();
			driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("phn");
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
			driver.findElement(By.linkText("Duplicate Lead")).click();
			driver.findElement(By.name("submitButton")).click();
	}
	@DataProvider
	public String[] duplicatedata() {
		String[] data=new String[2];
		data[0]="91";
		data[1]="99";
	return data;

}
}

package assignment10.day10;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class BaseSnow {
	
	public ChromeDriver driver;
	@Parameters({"URL","Name","Passowrd","Filter","Frame"})
	@BeforeMethod
	public void preCondition(String url,String uname,String pswd,String filters,String frame) throws InterruptedException {
		ChromeDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame(frame);
		driver.findElement(By.id("user_name")).sendKeys(uname);
		driver.findElement(By.id("user_password")).sendKeys(pswd);
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		WebElement filter = driver.findElement(By.id("filter"));
		filter.sendKeys(filters);
		filter.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}
@AfterMethod
public void postCondition() {
		driver.close();
	}

}

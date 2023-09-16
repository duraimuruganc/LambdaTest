package SeleniumPackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleForm {
	
	private WebDriver driver;
	
	
	@BeforeClass
	public void SetUp() throws MalformedURLException
	{
		String hubURL = "https://hub.lambdatest.com/wd/hub";
		
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("88.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "duraimurugan01.007");
		ltOptions.put("accessKey", "APc3qVOgD2ea4f7sRTues9b2JTvB2AdhYsQrhzhQw5mtB4SHhy");
		ltOptions.put("visual", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "Untitled");
		ltOptions.put("console", "true");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
	}
	
	@Test
	public void SimpleFormTest()
	{
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]")).click();
		String currentUrl=driver.getCurrentUrl();
		if(currentUrl.contains("simple-form-demo"))
		{
			System.out.println("Validation Pass");
		}
		else 
		{
			System.out.println("Validtion Fail");
		}
		String inputMessage = "Welcome to LambdaTest";
		driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(inputMessage);
		driver.findElement(By.xpath("//button[@id='showInput']")).click();
		String outMessage = driver.findElement(By.xpath("//p[@id='message']")).getText();
		if(inputMessage.equals(outMessage))
		{
			System.out.println("Valid Message");
		}
		else
		{
			System.out.println("Invalid Message");
		}
	}
	
	
	@AfterClass
	public void TearDown()
	{
		driver.quit();
	}

}

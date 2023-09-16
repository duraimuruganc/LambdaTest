package SeleniumPackage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SimpleFormTest {

    private WebDriver driver;

    @BeforeMethod
    @Parameters({ "browser", "browserVersion", "os" })
    public void setUp(String browser, String browserVersion, String os) throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        
        // Create browser-specific desired capabilities
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", "duraimurugan01.007"); 
        ltOptions.put("accessKey", "APc3qVOgD2ea4f7sRTues9b2JTvB2AdhYsQrhzhQw5mtB4SHhy"); 
        ltOptions.put("visual", true);
		ltOptions.put("network", true);
		ltOptions.put("project", "Untitled");
		ltOptions.put("console", "true");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
        // Other LambdaTest capabilities (visual, network, etc.) can be added here
        
        if ("Chrome".equalsIgnoreCase(browser)) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName(os);
            browserOptions.setBrowserVersion(browserVersion);
            browserOptions.setCapability("LT:Options", ltOptions);
            driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
        } else if ("msedge".equalsIgnoreCase(browser)) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setPlatformName(os);
            edgeOptions.setBrowserVersion(browserVersion);
            edgeOptions.setCapability("LT:Options", ltOptions);
            driver = new RemoteWebDriver(new URL(hubURL), edgeOptions);
        } else if ("Firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPlatformName(os);
            firefoxOptions.setBrowserVersion(browserVersion);
            firefoxOptions.setCapability("LT:Options", ltOptions);
            driver = new RemoteWebDriver(new URL(hubURL), firefoxOptions);
        } else if ("Internet Explorer".equalsIgnoreCase(browser)) {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.setPlatformName(os);
            ieOptions.setBrowserVersion(browserVersion);
            ieOptions.setCapability("LT:Options", ltOptions);
            driver = new RemoteWebDriver(new URL(hubURL), ieOptions);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    @Test
    public void testSimpleForm() {
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

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

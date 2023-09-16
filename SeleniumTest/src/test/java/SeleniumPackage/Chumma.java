package SeleniumPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class Chumma {

    public static final String USERNAME = "your_username"; // Replace with your LambdaTest username
    public static final String ACCESS_KEY = "your_access_key"; // Replace with your LambdaTest access key
    public static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Define test capabilities
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setCapability("build", "Test Scenario 1");
        capabilities.setCapability("name", "LambdaTest Automation");

        // Initialize WebDriver
        WebDriver driver = new RemoteWebDriver(new URL(GRID_URL), capabilities);

        // 1. Open LambdaTest’s Selenium Playground
        driver.get("https://www.lambdatest.com/selenium-playground");

        // 2. Click “Simple Form Demo” under Input Forms.
        WebElement simpleFormDemoLink = driver.findElement(By.partialLinkText("Simple Form Demo"));
        simpleFormDemoLink.click();

        // 3. Validate that the URL contains “simple-form-demo”.
        if (driver.getCurrentUrl().contains("simple-form-demo")) {
            System.out.println("URL contains 'simple-form-demo'.");
        } else {
            System.out.println("URL does not contain 'simple-form-demo'.");
        }

        // 4. Create a variable for a string value, e.g., “Welcome to LambdaTest”.
        String message = "Welcome to LambdaTest";

        // 5. Use this variable to enter values in the “Enter Message” text box.
        WebElement messageInput = driver.findElement(By.id("user-message"));
        messageInput.sendKeys(message);

        // 6. Click “Get Checked Value”.
        WebElement showMessageButton = driver.findElement(By.cssSelector("button[onclick='showInput();']"));
        showMessageButton.click();

        // Wait for a moment to ensure the result is displayed
        Thread.sleep(2000);

        // 7. Validate whether the same text message is displayed in the right-hand panel under the “Your Message:” section.
        WebElement outputMessage = driver.findElement(By.id("display"));
        if (outputMessage.getText().equals("Your Message: " + message)) {
            System.out.println("Message validation passed.");
        } else {
            System.out.println("Message validation failed.");
        }

        // Quit the WebDriver
        driver.quit();
    }
}


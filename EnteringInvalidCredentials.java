package loginPage_validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnteringInvalidCredentials {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);

		driver.get("https://app-staging.nokodr.com/");

		driver.findElement(By.name("username")).sendKeys("123@yahoo.com"); // Invalid email
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Invalid123!"); // Invalid password

		driver.findElement(By.id("rememberMe")).click();

		driver.findElement(By.xpath("//div[text()='Log In']")).click();

		String errorMessage = driver.findElement(By.xpath("//h2[text()='Invalid Email or Password']")).getText();
		System.out.println(errorMessage);

		// Verify mandatory fields
		String username = driver.findElement(By.name("username")).getAttribute("value");
		String password = driver.findElement(By.xpath("//input[@name='password']")).getAttribute("value");

		if (username.isEmpty()) {
			System.out.println("Username field is mandatory.");
		}
		if (password.isEmpty()) {
			System.out.println("Password field is mandatory.");
		}

		driver.quit();
	}
}
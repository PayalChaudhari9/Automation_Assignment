package loginPage_validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnteringValidCredentials {
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS); 

		
		driver.get("https://app-staging.nokodr.com/");

		
		driver.findElement(By.name("username")).sendKeys("pmchaudhari910@gmail.com");
		driver.findElement(By.xpath("(//input['name=\"password\"'])[2]")).sendKeys("Payal@888");

		
		driver.findElement(By.id("rememberMe")).click();

		
		driver.findElement(By.xpath("//div[text()='Log In']")).click(); 

		
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("dashboard")) {
			System.out.println("Login successful, redirected to dashboard.");
		} else {
			System.out.println("Login failed, not redirected to dashboard.");
		}

		
		String username = driver.findElement(By.name("username")).getAttribute("value");
		String password = driver.findElement(By.xpath("(//input['name=\\\"password\\\"'])[2]")).getAttribute("value");

		if (username.isEmpty()) {
			System.out.println("Username field is mandatory.");
		}
		if (password.isEmpty()) {
			System.out.println("Password field is mandatory.");
		}

		
		driver.quit();
	}
}
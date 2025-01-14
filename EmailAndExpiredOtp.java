package loginPage_validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmailAndExpiredOtp {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);

		driver.get("https://app-staging.nokodr.com/");

		driver.findElement(By.xpath("//span[text()=' OTP ']")).click();

		driver.findElement(By.xpath("//input['type=\"email\"']")).sendKeys("pmchaudhari910@gmail.com");

		driver.findElement(By.xpath("//div[text()='Send Code']")).click();

		String errorMes = driver.findElement(By.xpath("//h2[text()='User not found']")).getText();
		System.out.println(errorMes);
		try {
			Thread.sleep(60000); // Wait for 60 seconds (or however long the OTP is valid)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Enter the expired OTP
		driver.findElement(By.xpath("//span[text()=' OTP ']")).sendKeys("123456"); // Use the OTP received, assuming
																					// it's expired

		driver.findElement(By.xpath("//button[text()='Log In']")).click(); //

		String errorMessage = driver.findElement(By.xpath("//h2[text()='Invalid or Expired code']")).getText();
		System.out.println(errorMessage);

		driver.quit();
	}
}
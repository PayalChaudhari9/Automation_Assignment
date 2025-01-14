package signupPage_validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LongNameValidation {

    public static void main(String[] args) throws InterruptedException {
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("https://app-staging.nokodr.com/");
        
        WebElement signUpLink = driver.findElement(By.linkText("Sign up"));
        signUpLink.click();
        
        WebElement emailField = driver.findElement(By.xpath("(//input['name=\"username\"'])[4]"));
        String email = "shivanichaudhari109@gmail.com"; // Valid email
        emailField.sendKeys(email);
        
        WebElement checkbox = driver.findElement(By.xpath("(//input['name=\"agreeToTerms\"'])[5]/parent::div/label/span"));
        checkbox.click();	
        
        WebElement proceedButton = driver.findElement(By.xpath("//div[text()='Proceed']"));
        proceedButton.click();
        
        WebElement messageElement = driver.findElement(By.xpath("/html/body/div[6]/section/abx-toaster/div/div"));
        String message = messageElement.getText();
        System.out.println(message);
		
        WebElement verificationCodeField = driver.findElement(By.xpath("(//input['placeholder=\"Enter Code\"'])[4]"));
        String verificationCode = "985282"; // Valid verification code
        verificationCodeField.sendKeys(verificationCode);
       
        WebElement verifyCodeButton = driver.findElement(By.xpath("//div[text()='Verify Code']"));
        verifyCodeButton.click();
        
        String signUpPageTitle = "signUp";
        String actual_title = driver.getTitle();
        
        if (actual_title.contains(signUpPageTitle)) {
            System.out.println("Code verified!");
            System.out.println("You are at SignUp page");
            
            WebElement firstNameField = driver.findElement(By.xpath("(//input[@name='firstName'])[4]"));
            String firstName = "ThisIsAVeryLongFirstNameThatExceedsTheLimit"; // Long first name
            firstNameField.sendKeys(firstName);
            
            WebElement lastNameField = driver.findElement(By.xpath("(//input[@name='lastName'])[4]"));
            String lastName = "ThisIsAVeryLongLastNameThatExceedsTheLimit"; // Long last name
            lastNameField.sendKeys(lastName);
            
            // Check for length of first name
            if (firstName.length() > 30) { // Assuming 30 is the maximum allowed length
                System.out.println("First name is too long. Maximum length is 30 characters.");
            }
            
            // Check for length of last name
            if (lastName.length() > 30) { // Assuming 30 is the maximum allowed length
                System.out.println("Last name is too long. Maximum length is 30 characters.");
            }
            
            // Proceed only if names are valid
            if (firstName.length() <= 30 && lastName.length() <= 30) {
                WebElement passwordField = driver.findElement(By.xpath("(//input[@name='password'])[4]"));
                String password = "Payal@123"; // Password
                passwordField.sendKeys(password);
                
                WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@name='password-confirmpassword']")); 
                confirmPasswordField.sendKeys(password); // Enter the same password for confirmation
                
                // Check if password and confirm password match
                if (password.equals(confirmPasswordField.getAttribute("value"))) {
                    WebElement registerButton = driver.findElement(By.xpath("//div[text()='Register']"));
                    registerButton.click();
                    System.out.println("Account created successfully!");
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
            } else {
                System.out.println("Please correct the names and try again.");
            }
        } else {
            System.out.println("Invalid or expired verification code");
            System.out.println("You are not at SignUp page");
        }
        
        driver.quit();
    }
}
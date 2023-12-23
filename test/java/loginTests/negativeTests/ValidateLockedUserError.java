package loginTests.negativeTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidateLockedUserError {

    @Test
    public void testSuccessfulLogin() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\motaz\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String correct_user="standard_user",
                incorrect_user="locked_out_user",
                incorrect_password="wrong password",
                correct_password = "secret_sauce";


        driver.get("https://www.saucedemo.com/");

        negativeLgoIn(driver, correct_user, incorrect_password, "Epic sadface: Username and password do not match any user in this service");

        negativeLgoIn(driver, incorrect_user, correct_password, "Epic sadface: Username and password do not match any user in this service");

        negativeLgoIn(driver, incorrect_user, incorrect_password, "Epic sadface: Username and password do not match any user in this service");

        negativeLgoIn(driver, "", correct_password, "Epic sadface: Username is required");

        negativeLgoIn(driver, correct_user, "", "Epic sadface: Password is required");

        negativeLgoIn(driver, "", "", "Epic sadface: Username is required");


    }

    public void negativeLgoIn(WebDriver driver, String username, String password,String message){
        driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();

        // Validate the error message
        WebElement errorElement = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String errorMessage = errorElement.getText();

        if (!message.equals(errorMessage)) {
            System.out.println( username + " + " + password);
            System.out.println("Expected: " + message);
            System.out.println("Actual: " + errorMessage);
        } else {
            System.out.println("Pass the test");
        }

        String currentUrl = driver.getCurrentUrl();
        if (!"https://www.saucedemo.com/".equals(currentUrl)) {
            System.out.println("Expected: https://www.saucedemo.com/");
            System.out.println("Actual: " + currentUrl);
        } else {
            System.out.println("URL validation passed");
        }
    }

}

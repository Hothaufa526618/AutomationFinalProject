package loginTests.positiveTests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidateStandardUserLogin {
    @Test
    public void testSuccessfulLogin() {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\motaz\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        String[] users = {"standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"};
        String password = "secret_saouse";

        for (String user : users) {
            WebElement usernameInput = driver.findElement(By.id("user-name"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));
            usernameInput.sendKeys(user);
            passwordInput.sendKeys(password);
            loginButton.click();


            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
                System.out.println("URL validation passed.");
            } else {
                System.out.println("URL validation failed. Actual URL: " + currentUrl);
            }

            String pageTitle = driver.getTitle();
            if (pageTitle.equals("Swag Labs")) {
                System.out.println("Title validation passed.");
            } else {
                System.out.println("Title validation failed. Actual Title: " + pageTitle);
            }

        }
        driver.quit();

    }
}

package sanityTests;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.Assert.assertEquals;

public class BuyProductsScenario {
    @Test
    public void testBuyProductsScenario() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\motaz\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("password");
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();

        assertEquals("URL validation failed. Expected: " + "https://www.saucedemo.com/inventory.html" + ", Actual: " + currentUrl, "https://www.saucedemo.com/inventory.html", currentUrl);
        assertEquals("Title validation failed. Expected: " + "Swag Labs" + ", Actual: " + currentTitle, "Swag Labs", currentTitle);


        WebElement item1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement item2 = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));

        item1.click();
        item2.click();

        WebElement cartIcon = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        int actualItemCount = Integer.parseInt(cartIcon.getText());

        assertEquals("Cart icon validation failed. Expected items: " + 2 + ", Actual items: " + actualItemCount, 2, actualItemCount);

        WebElement cart = driver.findElement(By.cssSelector(".shopping_cart_link"));
        cart.click();

        currentUrl = driver.getCurrentUrl();
        currentTitle = driver.getTitle();
        assertEquals("URL validation failed. Expected: " + "https://www.saucedemo.com/cart.html" + ", Actual: " + currentUrl, "https://www.saucedemo.com/cart.html", currentUrl);
        assertEquals("Title validation failed. Expected: " + "Your Cart" + ", Actual: " + currentTitle, "Your Cart", currentTitle);

        int itemsCount = driver.findElements(By.cssSelector(".cart_item")).size();

        assertEquals("Items in cart validation failed. Expected items: " + 2 + ", Actual items: " + itemsCount, 2, itemsCount);

        driver.findElement(By.cssSelector(".checkout_button")).click();

        currentTitle = driver.getTitle();
        assertEquals("Title validation failed. Expected: " + "Checkout: Your Information" + ", Actual: " + currentTitle, "Checkout: Your Information", currentTitle);

        String firstName = "motaz";
        String lastName = "zoubi";
        String zipCode = "189500";
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement zipCodeInput = driver.findElement(By.id("postal-code"));
        WebElement continueButton = driver.findElement(By.cssSelector(".checkout_button"));

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        zipCodeInput.sendKeys(zipCode);
        continueButton.click();
        // Step 11: Click the Continue button to navigate to the next step
        driver.findElement(By.cssSelector(".checkout_button")).click();

        currentTitle = driver.getTitle();
        currentUrl = driver.getCurrentUrl();

        assertEquals("Title validation failed. Expected: " + "Checkout: Overview" + ", Actual: " + currentTitle, "Checkout: Overview", currentTitle);
        assertEquals("url validation failed. Expected: " + "https://www.saucedemo.com/checkout-step-two.html" + ", Actual: " + currentUrl,"https://www.saucedemo.com/checkout-step-two.html", currentUrl);


        driver.findElement(By.cssSelector(".cart_button")).click();


        currentTitle = driver.getTitle();
        currentUrl = driver.getCurrentUrl();

        assertEquals("Title validation failed. Expected: " + "Checkout: Complete!" + ", Actual: " + currentTitle, "Checkout: Complete!", currentTitle);
        assertEquals("url validation failed. Expected: " + "https://www.saucedemo.com/checkout-complete.html" + ", Actual: " + currentUrl,"https://www.saucedemo.com/checkout-complete.html", currentUrl);


        WebElement completeHeader = driver.findElement(By.cssSelector("#checkout_complete_container > h2"));
        WebElement completeText = driver.findElement(By.cssSelector("#checkout_complete_container > div"));

        assertEquals("Thank you for your order!", completeHeader.getText());
        assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", completeText.getText());

        driver.quit();


    }
}

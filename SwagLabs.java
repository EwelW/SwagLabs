import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwagLabs{
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void addOneToCart() throws InterruptedException {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(3000);
        List<WebElement> prod = driver.findElements(By.className("inventory_item"));
        for (int i = 0; i < prod.size(); i++) {
            String productName = prod.get(i).getText();


            if (productName.contains("Sauce Labs Backpack")) {
                driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("shopping_cart_container")).click();
        System.out.println("Price: " +driver.findElement(By.className("inventory_item_price")).getText()+"$");
        System.out.println("Qty: "+driver.findElement(By.className("cart_quantity")).getText());
        System.out.println("One product was added to the cart");
    }



    }


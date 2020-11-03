import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
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
    public void addOneToCart() throws InterruptedException, IOException {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(3000);
        List<WebElement> productList = driver.findElements(By.className("inventory_item"));
        for (int i = 0; i < productList.size(); i++) {
            String productName = productList.get(i).getText();


            if (productName.contains("Sauce Labs Backpack")) {
                driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("shopping_cart_container")).click();
        System.out.println("Price: " +driver.findElement(By.className("inventory_item_price")).getText()+"$");
        System.out.println("Qty: "+driver.findElement(By.className("cart_quantity")).getText());
        System.out.println("One product was added to the cart");
        File scrn=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrn, new File("C://Users//Dell//IdeaProjects/Scrn.png"));
    }



    }


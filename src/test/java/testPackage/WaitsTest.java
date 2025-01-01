package testPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsTest {

    @Test
    public void noWait () {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        boolean boxVisible = driver.findElement(By.id("box0")).isDisplayed();
        Assert.assertTrue(boxVisible);
        driver.quit();
    }

    @Test
    public void implicitWait(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        boolean boxVisible = driver.findElement(By.id("box0")).isDisplayed();
        Assert.assertTrue(boxVisible);
        driver.quit();
    }


    @Test
    public void explicitWait(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        WebElement box = driver.findElement(By.id("box0"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> box.isDisplayed());
        Assert.assertTrue(box.isDisplayed());
        driver.quit();
    }

    @Test
    public void explicitWaitWithOption(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");
        driver.findElement(By.id("adder")).click();
        FluentWait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(2))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(NoSuchElementException.class);

        wait.until(
                d -> {
                    WebElement box = driver.findElement(By.id("box0"));
                    box.isDisplayed();
                    return true;
                });

        driver.quit();
    }
}

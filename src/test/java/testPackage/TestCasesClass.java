package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCasesClass {

    @Test
    public void validateTitle_TC1 (){
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        String title = driver.getTitle();
        Assert.assertEquals(title,"DuckDuckGo - Your protection, our priority.");
        driver.quit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateLogoVisibility_TC2 (){
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        WebElement logo = driver.findElement(By.cssSelector("section[class='header_headerLeft__gTUAg header_headerSection__C99zW'] img"));
        Assert.assertTrue(logo.isDisplayed());
        driver.quit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateSearchForSeleniumResult_TC3 (){
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("searchbox_input")).sendKeys("Selenium Webdriver");
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
        List<WebElement> resultsLinks = driver.findElements(By.className("Rn_JXVtoPVAFyGkcaXyK"));
        String firstResultLink = resultsLinks.getFirst().getText();
        Assert.assertEquals(firstResultLink,"https://www.selenium.dev › documentation › webdriver");
        driver.quit();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateSearchForTestNGResult_TC4 (){
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("searchbox_input")).sendKeys("Test NG");
        driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
        List<WebElement> resultsLinks = driver.findElements(By.className("Rn_JXVtoPVAFyGkcaXyK"));
//        System.out.println(resultsLinks.size());
        String fourthResult = resultsLinks.get(3).getText();
//        System.out.println(firstResultLink);
        Assert.assertTrue(fourthResult.contains("testng-tutorial"));
        driver.quit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateCheckBox_TC6 (){
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        List<WebElement> allBoxes = driver.findElements(By.xpath("//input"));
        System.out.println(allBoxes.size());
        allBoxes.getFirst().click();

        for (WebElement checkbox : allBoxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.quit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateCountryInTable_TC7 (){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        WebElement table = driver.findElement(By.id("customers"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
//        System.out.println("Number of rows =" +rows.size());

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
//            System.out.println(cells.size());

            if (!(cells.size() == 0)     && cells.get(0).getText().equals("Ernst Handel")){
                String country = cells.get(2).getText();
                System.out.println(country);
                Assert.assertEquals(country,"Austria");
            }

        }

        driver.quit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateUploadFile_TC8 (){
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement uploader = driver.findElement(By.id("file-upload"));
        uploader.sendKeys("/Users/assem/Downloads/IMG_5694.thumbnail.jpg");
        driver.findElement(By.id("file-submit")).click();

       WebElement uploadedFile =  driver.findElement(By.id("uploaded-files"));
       Assert.assertTrue(uploadedFile.isDisplayed());
       Assert.assertTrue(uploadedFile.getText().contains("IMG_5694.thumbnail.jpg"));
       driver.quit();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateDragDropFile_TC9 () {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement dropArea = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable,dropArea).perform();

        String successMsg = driver.findElement(By.xpath("//*[@id=\"droppable\"]/p")).getText();
        Assert.assertEquals(successMsg,"Dropped!");
        driver.quit();
    }

}

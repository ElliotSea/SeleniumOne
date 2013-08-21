package SeleniumTests;

import com.sun.jna.platform.FileUtils;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Tests {

    private WebDriver driver;
    private static ChromeDriverService service;

    @Before
    public void setUp() throws IOException {
        //Chrome
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\ChromeDriver\\chromedriver_win32_2.2\\chromedriver.exe"))
                .usingAnyFreePort()
                .withLogFile(new File("C:\\ChromeDriver\\chromedriver_win32_2.2\\chromedriver.log"))
                .build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
        driver.navigate().to("https://www.google.com");

        //Firefox
       // driver = new FirefoxDriver();
       // driver.navigate().to("https://www.google.com");
    }
    @After
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void startWebDriver () {
        Assert.assertTrue("Starts with google", driver.getTitle().startsWith("Google"));
        Assert.assertEquals("https://www.google.com/", driver.getCurrentUrl());
    }
    @Test
    public void UsingSearch() throws IOException {
        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Sergii");
        query.submit();

        long start = System.currentTimeMillis();
        new WebDriverWait(driver, 20, 50).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/div/div/div/span")));

        List<WebElement> el = driver.findElements(By.xpath("//li/div/div/div/span"));

        for (WebElement item : el) {
            System.out.println(item.getText());
        }

        System.out.println("Found in "+(System.currentTimeMillis()-start)+" ms");
       // String target ="C:/1.png";
       // File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       // org.apache.commons.io.FileUtils.copyFile(scrFile, new File(target));
        Assert.assertEquals(10,el.size() );
    }
}

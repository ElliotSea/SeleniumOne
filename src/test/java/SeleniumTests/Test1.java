package SeleniumTests;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test1 {

    @Test
    public void startWebDriver (){
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://google.com");

        Assert.assertTrue("Starts with google", driver.getTitle().startsWith("Google"));
        driver.quit();


    }

}

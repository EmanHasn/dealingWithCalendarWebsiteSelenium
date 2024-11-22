package DateCal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class P2_CalculationPage {
    WebDriver driver;
    public P2_CalculationPage(WebDriver driver){
        this.driver=driver;
    }
    public void results()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.className("panel-title")));
    }
}
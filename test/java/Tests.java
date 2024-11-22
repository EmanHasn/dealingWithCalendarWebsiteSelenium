import DateCal.P1_DateCalculator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Tests {
    WebDriver driver;
    @BeforeMethod
    public void setDriver() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.bestcase.com/date-calculator/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test(priority = 2)
    public void DateInFuture() throws InterruptedException {
        new P1_DateCalculator(driver).DateInFuture().results();
    }
    @Test (priority = 3)
    public void DateInPast() throws InterruptedException {
        new P1_DateCalculator(driver).DateInPast().results();
    }
    @Test (priority = 1)
    public void TodayDate() {
        new P1_DateCalculator(driver).DateToday().results();
    }
    @AfterMethod
    public void closeUrl()
    {
      driver.quit();
    }
}

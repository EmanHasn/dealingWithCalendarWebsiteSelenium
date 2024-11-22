package DateCal;

import HelperFun.DynamicLocators;
import HelperFun.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class P1_DateCalculator {
    WebDriver driver;
    By DateInput = By.id("txtStartDate");
    By Table = By.className("table-condensed");
    By MonthAndYearBox = By.xpath("//tr//preceding-sibling::tr//following-sibling::th[@class='datepicker-switch']");
    By SubmitBtn = By.xpath("//input[@type='submit']");

    public P1_DateCalculator(WebDriver driver) {
        this.driver = driver;
    }

    public P2_CalculationPage DateInFuture() throws InterruptedException {
        new Helpers(driver).FormateDate(12, "following-sibling");
        return new P2_CalculationPage(driver);

    }
    // Date in the past (35 years ago)
    public P2_CalculationPage DateInPast() throws InterruptedException {
        new Helpers(driver).FormateDate(35,"preceding-sibling");
        return new P2_CalculationPage(driver);
    }
    // Today's date
    public P2_CalculationPage DateToday() {

        LocalDate todayDate = LocalDate.now();
        System.out.println(todayDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = todayDate.format(formatter);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(DateInput));

        driver.findElement(DateInput).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(Table));

      while (!(driver.findElement(MonthAndYearBox).getText().contains(formattedDate))) {
          if (Integer.parseInt(formattedDate.split(" ")[1]) > (LocalDate.now().getYear())) {
              driver.findElement(DynamicLocators.getBtnLocator("preceding-sibling")).click();
          } else if (Integer.parseInt(formattedDate.split(" ")[1]) < (LocalDate.now().getYear())) {
              driver.findElement(DynamicLocators.getBtnLocator("following-sibling")).click();
          }
      }
        Assert.assertEquals(driver.findElement(MonthAndYearBox).getText(),formattedDate, "Assert Month and Year");
        formatter = DateTimeFormatter.ofPattern("dd");
        String toDay = todayDate.format(formatter);

        By todayDateCell = By.xpath("//td[text()='" + toDay + "']");
        WebElement dateCell = driver.findElement(todayDateCell);
        Assert.assertEquals(dateCell.getText(),toDay, "Assert Day");
        dateCell.click();
        driver.findElement(SubmitBtn).click();

        return new P2_CalculationPage(driver);
    }
}

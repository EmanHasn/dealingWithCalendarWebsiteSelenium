package HelperFun;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helpers {
     WebDriver driver;
    //Locators
    static By DateInput = By.id("txtStartDate");
    static By Table = By.className("table-condensed");
    static By MonthAndYearBox = By.xpath("//tr//preceding-sibling::tr//following-sibling::th[@class='datepicker-switch']");
    static By SubmitBtn = By.xpath("//input[@type='submit']");

    public Helpers(WebDriver driver) {
        this.driver = driver;
    }
    public  void FormateDate(int years, String nextORPre) throws InterruptedException {
        LocalDate givenDate = (years==12)? LocalDate.now().plusYears(years): LocalDate.now().minusYears(years);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String formattedDate = givenDate.format(formatter);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(DateInput));

        driver.findElement(DateInput).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(Table));
        while (!(driver.findElement(MonthAndYearBox).getText().contains(formattedDate))) {
            driver.findElement(DynamicLocators.getBtnLocator(nextORPre)).click();
        }

        Assert.assertEquals(driver.findElement(MonthAndYearBox).getText(),formattedDate, "Assert Month and Year");

        formatter = DateTimeFormatter.ofPattern("dd");
        String givenDay = givenDate.format(formatter);
        By givenDateCell = By.xpath("//td[text()='" + givenDay + "']");
        WebElement dateCell = driver.findElement(givenDateCell);
        Assert.assertEquals(dateCell.getText(),givenDay, "Assert Day");
        dateCell.click();
        driver.findElement(SubmitBtn).click();
    }
}

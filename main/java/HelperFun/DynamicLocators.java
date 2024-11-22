package HelperFun;

import org.openqa.selenium.By;

public class DynamicLocators {
    public static By getBtnLocator(String label) {
        return By.xpath("//tr//preceding-sibling::tr//following-sibling::th[@class='datepicker-switch']//" + label + "::th");
    }
}

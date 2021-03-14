package com.woveny.wovenyproductsgenerator.helper

import com.woveny.wovenyproductsgenerator.exception.ElementNotFoundException
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait

fun WebDriver.waitForPageLoaded() {
    val expectation: ExpectedCondition<Boolean> =
        ExpectedCondition { driver -> (driver as JavascriptExecutor?)!!.executeScript("return document.readyState") == "complete" }
    val wait: Wait<WebDriver> = WebDriverWait(this, 3)
    try {
        wait.until(expectation)
    } catch (error: Throwable) {
        println("Timeout waiting for Page Load Request to complete.")
    }
}

fun WebDriver.waitForElementByCssAppear(cssSelector: String) {
    val wait = WebDriverWait(this, 3)
    try {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)))
    } catch (error: Throwable) {
        println("Timout waiting for element with XPath: $cssSelector to appear.")
    }
}

fun WebDriver.findElementByCssSafe(cssSelector: String): WebElement {
    val searchedElements = this.findElements(By.cssSelector(cssSelector))
    return if (searchedElements.size > 0) searchedElements[0] else throw ElementNotFoundException("Element not exist")
}

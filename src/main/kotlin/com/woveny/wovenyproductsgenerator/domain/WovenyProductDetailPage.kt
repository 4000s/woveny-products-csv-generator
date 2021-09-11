package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class WovenyProductDetailPage(private val webDriver: WebDriver) {

    @FindBy(css = "div.pull-right > button:nth-child(1)")
    private val saveButton: WebElement? = null

    private val wait = WebDriverWait(webDriver, 10)

    fun saveAndClose() {
        wait.until(ExpectedConditions.visibilityOfAllElements(saveButton))
        wait.until(ExpectedConditions.elementToBeClickable(saveButton))

        saveButton?.click()
        webDriver.waitForPageLoaded()
    }
}

package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class WovenyProductDetailPage(private val webDriver: WebDriver) {

    @FindBy(css = "div.pull-right > button:nth-child(1)")
    private val saveButton: WebElement? = null

    fun saveAndClose() {
        saveButton?.click()
        webDriver.waitForPageLoaded()
    }
}

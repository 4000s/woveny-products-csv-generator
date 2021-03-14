package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.findElementByCssSafe
import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class WovenyProductsPage(private val webDriver: WebDriver) {

    @FindBy(css = "#input-model")
    private val modelInput: WebElement? = null

    @FindBy(css = "#button-filter")
    private val filterButton: WebElement? = null

    fun findProduct(sku: String): WovenyProductDetailPage {
        modelInput?.clear()
        modelInput?.sendKeys(sku)
        filterButton?.click()
        webDriver.waitForPageLoaded()

        val productEditButton =
            webDriver.findElementByCssSafe("a.btn-primary:nth-child(1)")
        productEditButton.click()
        webDriver.waitForPageLoaded()

        val wovenyProductDetailPage = WovenyProductDetailPage(webDriver)
        PageFactory.initElements(webDriver, wovenyProductDetailPage)
        return wovenyProductDetailPage
    }
}

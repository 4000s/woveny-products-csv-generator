package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions

import org.openqa.selenium.support.ui.WebDriverWait


class WovenyProductsPage(private val webDriver: WebDriver) {

    @FindBy(css = "#input-model")
    private val modelInput: WebElement? = null

    @FindBy(css = "#button-filter")
    private val filterButton: WebElement? = null

    private val jsExecutor = webDriver as JavascriptExecutor

    private val wait = WebDriverWait(webDriver, 2)

    fun findProduct(sku: String): WovenyProductDetailPage {
        wait.until(ExpectedConditions.visibilityOfAllElements(modelInput, filterButton))
        wait.until(ExpectedConditions.elementToBeClickable(filterButton))

        modelInput?.clear()
        modelInput?.sendKeys(sku)
        filterButton?.click()
        webDriver.waitForPageLoaded()

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='$sku']/../td[last()]")))

        val productEditButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='$sku']/../td[last()]")))

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", productEditButton);
        jsExecutor.executeScript("arguments[0].style.border='2px solid red'", productEditButton)

        productEditButton.click()
        webDriver.waitForPageLoaded()

        val wovenyProductDetailPage = WovenyProductDetailPage(webDriver)
        PageFactory.initElements(webDriver, wovenyProductDetailPage)
        return wovenyProductDetailPage
    }
}

package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class WovenyAdminDashboardPage(private val webDriver: WebDriver) {

    @FindBy(css = "#menu-catalog > a:nth-child(1)")
    private val catalog: WebElement? = null

    private val wait = WebDriverWait(webDriver, 10)

    fun clickOnProducts(): WovenyProductsPage {
        wait.until(ExpectedConditions.visibilityOfAllElements(catalog))
        wait.until(ExpectedConditions.elementToBeClickable(catalog))

        catalog?.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse1 > li:nth-child(2) > a:nth-child(1)")))

        val products = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#collapse1 > li:nth-child(2) > a:nth-child(1)")))

        products.click()
        webDriver.waitForPageLoaded()

        val wovenyProductsPage = WovenyProductsPage(webDriver)
        PageFactory.initElements(webDriver, wovenyProductsPage)
        return wovenyProductsPage
    }
}

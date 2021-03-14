package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.waitForElementByCssAppear
import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class WovenyAdminDashboardPage(private val webDriver: WebDriver) {

    @FindBy(css = "#menu-catalog > a:nth-child(1)")
    private val catalog: WebElement? = null

    fun clickOnProducts(): WovenyProductsPage {
        catalog?.click()
        webDriver.waitForElementByCssAppear("#collapse1 > li:nth-child(2) > a:nth-child(1)")

        val products = webDriver.findElement(By.cssSelector("#collapse1 > li:nth-child(2) > a:nth-child(1)"))
        products.click()
        webDriver.waitForPageLoaded()

        val wovenyProductsPage = WovenyProductsPage(webDriver)
        PageFactory.initElements(webDriver, wovenyProductsPage)
        return wovenyProductsPage
    }
}

package com.woveny.wovenyproductsgenerator.domain

import com.woveny.wovenyproductsgenerator.helper.waitForPageLoaded
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class WovenyLoginPage(private val webDriver: WebDriver) {
    init {
        webDriver.get("https://www.woveny.com/admin")
        PageFactory.initElements(webDriver, this)
    }

    @FindBy(css = "#input-username")
    private val userInput: WebElement? = null

    @FindBy(css = "#input-password")
    private val passInput: WebElement? = null

    @FindBy(css = ".btn")
    private val loginButton: WebElement? = null

    private val wait = WebDriverWait(webDriver, 2)

    fun login(user: String, pass: String): WovenyAdminDashboardPage {
        wait.until(ExpectedConditions.visibilityOfAllElements(userInput, passInput, loginButton))
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))

        userInput?.clear()
        userInput?.sendKeys(user)

        passInput?.clear()
        passInput?.sendKeys(pass)

        loginButton?.click()
        webDriver.waitForPageLoaded()

        val wovenyAdminDashboardPage = WovenyAdminDashboardPage(webDriver)
        PageFactory.initElements(webDriver, wovenyAdminDashboardPage)
        return wovenyAdminDashboardPage
    }
}

package com.woveny.wovenyproductsgenerator.controller

import com.woveny.wovenyproductsgenerator.domain.DriverType
import com.woveny.wovenyproductsgenerator.domain.DriverType.*
import com.woveny.wovenyproductsgenerator.domain.request.RefreshRequest
import com.woveny.wovenyproductsgenerator.service.WovenyRefreshProductService
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.opera.OperaDriver
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class WovenyRefreshProductController(val wovenyRefreshProductService: WovenyRefreshProductService) {

    var webDriver: WebDriver? = null

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    fun refreshProducts(refreshRequest: RefreshRequest): ResponseEntity<String> {
        webDriver = getWebDriver(refreshRequest.driver)

        return ResponseEntity.ok(wovenyRefreshProductService.refreshProducts(refreshRequest, webDriver!!))
    }

    private fun getWebDriver(requestedDriver: DriverType): WebDriver {
        return when (requestedDriver) {
            FIREFOX -> {
                WebDriverManager.firefoxdriver().setup()
                FirefoxDriver()
            }

            CHROME -> {
                WebDriverManager.chromedriver().setup()
                ChromeDriver()
            }

            OPERA -> {
                WebDriverManager.operadriver().setup()
                OperaDriver()
            }

            EDGE -> {
                WebDriverManager.edgedriver().setup()
                EdgeDriver()
            }

            I_EXPLORER -> {
                WebDriverManager.iedriver().setup()
                InternetExplorerDriver()
            }
        }
    }
}

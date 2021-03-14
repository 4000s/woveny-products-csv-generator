package com.woveny.wovenyproductsgenerator.service

import com.woveny.wovenyproductsgenerator.domain.WovenyLoginPage
import com.woveny.wovenyproductsgenerator.domain.request.RefreshRequest
import com.woveny.wovenyproductsgenerator.exception.ElementNotFoundException
import org.openqa.selenium.WebDriver
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class WovenyRefreshProductService {

    @Async
    fun refreshProducts(refreshRequest: RefreshRequest, webDriver: WebDriver) {
        val wovenyLoginPage = WovenyLoginPage(webDriver)
        val wovenyAdminDashboard = wovenyLoginPage.login(refreshRequest.user, refreshRequest.pass)
        val wovenyProductsPage = wovenyAdminDashboard.clickOnProducts()

        var counter = 1
        for (sku in refreshRequest.startSku..refreshRequest.endSku) {
            val modifiedSku = String.format("%04d", sku)

            val wovenyProductDetailPage = try {
                wovenyProductsPage.findProduct(modifiedSku)
            } catch (ex: ElementNotFoundException) {
                println("Product with sku: $modifiedSku not found!")
                continue
            }

            wovenyProductDetailPage.saveAndClose()
            println("$counter Product with sku: $modifiedSku refreshed.")
            counter++
        }

        webDriver.close()
    }
}

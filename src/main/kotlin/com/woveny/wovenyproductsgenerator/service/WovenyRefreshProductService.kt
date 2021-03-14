package com.woveny.wovenyproductsgenerator.service

import com.woveny.wovenyproductsgenerator.domain.WovenyLoginPage
import com.woveny.wovenyproductsgenerator.domain.request.RefreshRequest
import com.woveny.wovenyproductsgenerator.exception.ElementNotFoundException
import org.openqa.selenium.WebDriver
import org.springframework.stereotype.Service

@Service
class WovenyRefreshProductService {

    fun refreshProducts(refreshRequest: RefreshRequest, webDriver: WebDriver): String {
        val wovenyLoginPage = WovenyLoginPage(webDriver)
        val wovenyAdminDashboard = wovenyLoginPage.login(refreshRequest.user, refreshRequest.pass)
        val wovenyProductsPage = wovenyAdminDashboard.clickOnProducts()

        var counter = 1
        for (sku in refreshRequest.startSku..refreshRequest.endSku) {
            val wovenyProductDetailPage = try {
                wovenyProductsPage.findProduct(sku)
            } catch (ex: ElementNotFoundException) {
                println("Product with sku: $sku not found!")
                continue
            }

            wovenyProductDetailPage.saveAndClose()
            println("$counter Product with sku: $sku refreshed.")
            counter++
        }

        webDriver.close()
        return "${counter - 1} products refreshed"
    }
}

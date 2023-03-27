package com.woveny.wovenyproductsgenerator.controller

import com.woveny.wovenyproductsgenerator.domain.request.RefreshRequest
import com.woveny.wovenyproductsgenerator.service.WovenyRefreshProductService
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
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
        webDriver = WebDriverManager.getInstance(refreshRequest.driver).create()
        wovenyRefreshProductService.refreshProducts(refreshRequest, webDriver!!)
        return ResponseEntity.ok("Product Refresh has started!")
    }
}

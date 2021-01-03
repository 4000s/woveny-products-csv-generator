package com.woveny.wovenyproductsgenerator.controller

import com.woveny.wovenyproductsgenerator.service.WovenyProductGeneratorService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductCsvGeneratorController(private val wovenyProductGeneratorService: WovenyProductGeneratorService) {

    @PostMapping("/v1/generate")
    @ResponseStatus(HttpStatus.OK)
    fun generate(startIndex: String, endIndex: String): Unit {
        wovenyProductGeneratorService.generate(startIndex, endIndex)
    }
}
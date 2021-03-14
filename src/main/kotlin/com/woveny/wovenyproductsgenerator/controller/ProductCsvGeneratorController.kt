package com.woveny.wovenyproductsgenerator.controller

import com.woveny.wovenyproductsgenerator.domain.request.GenerateProductRequest
import com.woveny.wovenyproductsgenerator.service.WovenyProductGeneratorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class ProductCsvGeneratorController(private val wovenyProductGeneratorService: WovenyProductGeneratorService) {

    @PostMapping("/rugs/generate/")
    @ResponseStatus(HttpStatus.OK)
    fun generateRugs(generateProductRequest: GenerateProductRequest): ResponseEntity<String> {
        val numberOfRugs = wovenyProductGeneratorService.generateForRugs(generateProductRequest)
        return ResponseEntity.ok("Csv generation successfully finished, $numberOfRugs products created!")
    }

    @PostMapping("/pillows/generate")
    @ResponseStatus(HttpStatus.OK)
    fun generatePillows(generateProductRequest: GenerateProductRequest): ResponseEntity<String> {
        val numberOfPillows = wovenyProductGeneratorService.generateForPillows(generateProductRequest)
        return ResponseEntity.ok("Csv generation successfully finished, $numberOfPillows pillows created!")
    }
}
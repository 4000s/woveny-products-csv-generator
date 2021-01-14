package com.woveny.wovenyproductsgenerator.controller

import com.woveny.wovenyproductsgenerator.service.WovenyProductGeneratorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductCsvGeneratorController(private val wovenyProductGeneratorService: WovenyProductGeneratorService) {

    @PostMapping("/v1/rugs/generate/")
    @ResponseStatus(HttpStatus.OK)
    fun generateRugs(startIndex: String, endIndex: String): ResponseEntity<String> {
        return ResponseEntity.ok("Csv generation successfully finished, ${wovenyProductGeneratorService.generateForRugs(startIndex, endIndex)} products created!")
    }

    @PostMapping("/v1/pillows/generate")
    @ResponseStatus(HttpStatus.OK)
    fun generatePillows(startIndex: String, endIndex: String): ResponseEntity<String> {
        return ResponseEntity.ok("Csv generation successfully finished, ${wovenyProductGeneratorService.generateForPillows(startIndex, endIndex)} pillows created!")
    }
}
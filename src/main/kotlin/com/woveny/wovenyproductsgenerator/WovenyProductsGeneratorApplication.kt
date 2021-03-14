package com.woveny.wovenyproductsgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class WovenyProductsGeneratorApplication

fun main(args: Array<String>) {
    runApplication<WovenyProductsGeneratorApplication>(*args)
}

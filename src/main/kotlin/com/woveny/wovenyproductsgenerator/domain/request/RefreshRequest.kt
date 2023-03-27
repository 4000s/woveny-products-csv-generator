package com.woveny.wovenyproductsgenerator.domain.request

import io.github.bonigarcia.wdm.config.DriverManagerType

data class RefreshRequest(
    val user: String,
    val pass: String,
    val startSku: Int,
    val endSku: Int,
    val driver: DriverManagerType,
)

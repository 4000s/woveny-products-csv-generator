package com.woveny.wovenyproductsgenerator.domain.request

import com.woveny.wovenyproductsgenerator.domain.DriverType

data class RefreshRequest(
    val user: String,
    val pass: String,
    val startSku: Int,
    val endSku: Int,
    val driver: DriverType,
)

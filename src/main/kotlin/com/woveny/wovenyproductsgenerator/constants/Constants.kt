package com.woveny.wovenyproductsgenerator.constants

private val CSV_HEADERS = listOf(
    "model",
    "name",
    "description",
    "category",
    "quantity",
    "minimum",
    "subtract",
    "stock_status",
    "shipping",
    "status",
    "image",
    "additional_image",
    "price",
    "length",
    "width",
    "meta_keyword",
    "meta_title",
    "meta_description",
    "sort_order",
    "seo_keyword",
    "date_available",
    "skip_import",
    "attribute:Width",
    "attribute:Length",
    "attribute:Material",
    "attribute:Weave",
    "attribute:Style",
    "attribute:Color",
    "attribute:Region",
    "attribute:Age",
    "attribute:Size",
    "tag"
)

val CSV_HEADERS_FOR_RUG = CSV_HEADERS.toTypedArray()
val CSV_HEADERS_FOR_PILLOW = CSV_HEADERS.minus("attribute:Size").toTypedArray()

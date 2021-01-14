package com.woveny.wovenyproductsgenerator.service.property

import com.woveny.wovenyproductsgenerator.constants.*
import com.woveny.wovenyproductsgenerator.domain.SpreadSheetDocument
import kotlin.math.roundToInt

fun generateModel(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, MODEL)

fun generateNameWithSizes(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    val (widthFeet, widthInches) = cmToHeight(spreadSheetDocument.getCell(index, WIDTH))
    val (lengthFeet, lengthInches) = cmToHeight(spreadSheetDocument.getCell(index, LENGTH))
    return "${generateName(index, spreadSheetDocument)} - $widthFeet`$widthInches\" x $lengthFeet`$lengthInches\""
}

fun generateName(index: Int, spreadSheetDocument: SpreadSheetDocument) = spreadSheetDocument.getCell(index, NAME)

fun generateDescription(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    "<p><span style=\"color: rgb(119, 119, 119); font-family: Abel; font-size: 16px;\">${
        spreadSheetDocument.getCell(
            index,
            DESCRIPTION
        )
    }.</span><br></p><div><span style=\"color: rgb(119, 119, 119); font-family: Abel; font-size: 16px;\"><br></span></div> <ul><li><font face=\"Abel\" color=\"#777777\"><span style=\"font-size: 16px;\">Rare find, Unique handmade item. Only 1 in stock</span></font></li><li><font face=\"Abel\" color=\"#777777\"><span style=\"font-size: 16px;\">Free shipping Worldwide</span></font></li><li><font face=\"Abel\" color=\"#777777\"><span style=\"font-size: 16px;\">Usually shipped via FedEx or UPS</span></font></li><li><font face=\"Abel\" color=\"#777777\"><span style=\"font-size: 16px;\">Ready to ship in 1-3 business days and tracking information will be provided once shipping out</span></font></li><li><font face=\"Abel\" color=\"#777777\"><span style=\"font-size: 16px;\">Estimated delivery time 3-7 business days</span></font><br><br></li></ul><div><span style=\"color: rgb(119, 119, 119); font-family: Abel; font-size: 16px;\"><br></span></div>"
        .replace("..", ".")

fun generateCategory(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    when (spreadSheetDocument.getCell(index, CATEGORY)) {
        "Colorful Kilims" -> "RUGS:::RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Colorful Kilims"
        "Vintage Modern Kilims" -> "RUGS:::RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Vintage Modern Kilims"
        "Embroidered Kilims" -> "RUGS:::RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Embroidered Kilims"
        "Rag Rugs" -> "RUGS:::RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Rag Rugs"
        "Hemp Kilims" -> "RUGS:::RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Hemp Kilims"
        "Khotan | Caucasian Rugs" -> "RUGS:::RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Khotan | Caucasian Rugs"
        "Turkish Anatolian Rugs" -> "RUGS:::RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Turkish Anatolian Rugs"
        "Persian Rugs" -> "RUGS:::RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Persian Rugs"
        "Turkish Oushak Rugs" -> "RUGS:::RUGS///HANDKNOTTED RUGS:::RUGS///HANDKNOTTED RUGS///Turkish Oushak Rugs"
        "Mini Rugs | Door Mats" -> "Mini Rugs | Door Mats"
        "16\"x16\"(40x40cm) Pillows" -> "PILLOWS:::PILLOWS///16\"x16\"(40x40cm) Pillows"
        "16\"x24\"(40x60cm) Pillows" -> "PILLOWS:::PILLOWS///16\"x24\"(40x60cm) Pillows"
        "20\"x20\"(50x50cm) Pillows" -> "PILLOWS:::PILLOWS///20\"x20\"(50x50cm) Pillows"
        "14\"x20\"(35x50cm) Pillows" -> "PILLOWS:::PILLOWS///14\"x20\"(35x50cm) Pillows"
        "20\"x28\"(50x70cm) Pillows" -> "PILLOWS:::PILLOWS///20\"x28\"(50x70cm) Pillows"
        "12\"x24\"(30x60cm) Pillows" -> "PILLOWS:::PILLOWS///12\"x24\"(30x60cm) Pillows"
        "18\"x18\"(45x45cm) Pillows" -> "PILLOWS:::PILLOWS///18\"x18\"(45x45cm) Pillows"
        "12\"x20\"(30x50cm) Pillows" -> "PILLOWS:::PILLOWS///12\"x20\"(30x50cm) Pillows"
        "24\"x24\"(60x60cm) Pillows" -> "PILLOWS:::PILLOWS///24\"x24\"(60x60cm) Pillows"
        else -> throw IllegalArgumentException("category has typo: ${spreadSheetDocument.getCell(index, CATEGORY)}")
    }

fun generateQuantity() = 1
fun generateMinimum() = 1
fun generateSubtract() = 1
fun generateStockStatus() = "In Stock"
fun generateShipping() = 1
fun generateStatus() = 1

fun generateImage(index: Int, spreadSheetDocument: SpreadSheetDocument, startSku: String, endSku: String) =
    "catalog/$startSku-$endSku/${spreadSheetDocument.getCell(index, MODEL)}.jpg"

fun generateAdditionalImage(
    index: Int,
    spreadSheetDocument: SpreadSheetDocument,
    startSku: String,
    endSku: String
): String {
    val sku = spreadSheetDocument.getCell(index, MODEL)
    return when (spreadSheetDocument.getCell(index, CATEGORY)) {
        "16\"x16\"(40x40cm) Pillows",
        "16\"x24\"(40x60cm) Pillows",
        "20\"x20\"(50x50cm) Pillows",
        "14\"x20\"(35x50cm) Pillows",
        "20\"x28\"(50x70cm) Pillows",
        "12\"x24\"(30x60cm) Pillows",
        "18\"x18\"(45x45cm) Pillows",
        "12\"x20\"(30x50cm) Pillows",
        "24\"x24\"(60x60cm) Pillows" -> "catalog/$startSku-$endSku/${sku}A.jpg:::catalog/$startSku-$endSku/${sku}B.jpg"
        "Mini Rugs | Door Mats" -> "catalog/$startSku-$endSku/${sku}A.jpg:::catalog/$startSku-$endSku/${sku}B.jpg:::catalog/$startSku-$endSku/${sku}C.jpg"
        else -> "catalog/$startSku-$endSku/${sku}A.jpg:::catalog/$startSku-$endSku/${sku}B.jpg:::catalog/$startSku-$endSku/${sku}C.jpg:::catalog/$startSku-$endSku/${sku}D.jpg:::catalog/$startSku-$endSku/${sku}E.jpg"
    }
}

fun generatePrice(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, PRICE)

fun generateLength(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    "${spreadSheetDocument.getCell(index, LENGTH)}cm"

fun generateWidth(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    "${spreadSheetDocument.getCell(index, WIDTH)}cm"

fun generateMetaKeywordForRugs(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    return "Distressed Rug, Oushak Rug, Large Rug, Blue Beige Rug, Vintage Turkish Rug, Oushak Carpet, 9x12 Oushak Rug, Vintage Carpet, Area Rug 9x12, Turkish rug, Vintage Blue Rug, Turkish Vintage Carpet, Blue Rug, 9x12 Blue Rug, ${
        generateTagForRugs(
            index,
            spreadSheetDocument
        )
    }"
}

fun generateMetaKeywordForPillows(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    return "Decorative Pillow, Handmade Pillow, Kilim Pillow, Throw Pillow, Turkish Kilim Pillow, Home Decor, Striped Pillow, Colorful Pillows, Hemp Kilim Pillow, 16x16 Pillows, 20x20 Pillows, 16x24 Pillows, Cushion Cover, Pillow Cover, Kilim Pillow Cover, Kilim Cushion Cover, Turkish Handmade Pillows, White Pillows, White Black Pillows, ${
        generateTagForPillows(
            index,
            spreadSheetDocument
        )
    }"
}

fun generateMetaTitle(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, NAME)

fun generateMetaDescription(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    "${spreadSheetDocument.getCell(index, DESCRIPTION)}.".replace("..", ".")

fun generateSortOrder() = 1

fun generateSeoKeyword(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, NAME).toLowerCase().replace(Regex("(?:[^A-Za-z\\\\s] |[^A-Za-z\\s])"), "")
        .replace(" ", "-")
        .trim() + "-${spreadSheetDocument.getCell(index, MODEL)}"

fun generateDateAvailable() = "2019-03-01"
fun generateSkipImport() = 0

fun generateAttributeWidth(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    val widthCm = spreadSheetDocument.getCell(index, WIDTH)
    val (widthFeet, widthInches) = cmToHeight(widthCm)
    return "$widthFeet ` $widthInches \" ($widthCm cm)"
}

fun generateAttributeLength(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    val lengthCm = spreadSheetDocument.getCell(index, LENGTH)
    val (lengthFeet, lengthInches) = cmToHeight(lengthCm)
    return "$lengthFeet ` $lengthInches \" ($lengthCm cm)"
}

fun generateAttributeMaterial(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, MATERIAL)

fun generateAttributeWeave(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, WEAVE)

fun generateAttributeStyle(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, STYLE)

fun generateAttributeColor(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, COLOR)

fun generateAttributeRegion(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, REGION)

fun generateAttributeAge(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, AGE)

fun generateAttributeSize(index: Int, spreadSheetDocument: SpreadSheetDocument) =
    spreadSheetDocument.getCell(index, SIZE)

fun generateTagForRugs(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    val style = spreadSheetDocument.getCell(index, STYLE).replace(",", " Rug, ")
    val region = spreadSheetDocument.getCell(index, REGION).replace(",", " Rug, ")
    val sizeUnmodified = spreadSheetDocument.getCell(index, SIZE)
    val size =
        if (sizeUnmodified.equals("Runners", true)) sizeUnmodified else sizeUnmodified.replace(",", " Rug, ") + " Rug"
    val color = spreadSheetDocument.getCell(index, COLOR).replace(",", " Rug, ")
    val age = when (spreadSheetDocument.getCell(index, AGE).trim()) {
        "Vintage" -> "Vintage Turkish Rug"
        "Antique" -> "Antique Rug"
        else -> throw IllegalArgumentException("Unknown Age: ${spreadSheetDocument.getCell(index, AGE)}")
    }
    return "$style Rug, $region Rug, $size, $color Rug, $age"
}

fun generateTagForPillows(index: Int, spreadSheetDocument: SpreadSheetDocument): String {
    val width = spreadSheetDocument.getCell(index, WIDTH)
    val length = spreadSheetDocument.getCell(index, LENGTH)
    val style = spreadSheetDocument.getCell(index, STYLE).replace(",", " Pillow, ")
    val sizeInches = "${cmToInches(width)}\"x${cmToInches(length)}\""
    val sizeCentimeters = "${width}x${length}cm"
    val color = spreadSheetDocument.getCell(index, COLOR).replace(",", " Pillow, ")
    return "$style Pillow, $sizeInches Pillow, $sizeCentimeters Pillow, $color Pillow"
}

private fun cmToInches(cmString: String): Int {
    val cm = cmString.toInt()
    return (cm * 0.3937).roundToInt()
}

private fun cmToHeight(cmString: String): Pair<String, String> {
    val cm = cmString.toInt()
    val feet = (cm * 0.0328).toInt()
    val remaining = cm - (feet * 30.48)
    val inch = (remaining * 0.3937).roundToInt()
    return Pair(feet.toString(), inch.toString())
}
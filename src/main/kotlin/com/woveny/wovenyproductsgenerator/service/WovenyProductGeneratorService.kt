package com.woveny.wovenyproductsgenerator.service

import com.woveny.wovenyproductsgenerator.constants.CSV_HEADERS_FOR_RUG
import com.woveny.wovenyproductsgenerator.domain.SpreadSheetDocument
import com.woveny.wovenyproductsgenerator.service.property.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.stereotype.Service
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd")

@Service
class WovenyProductGeneratorService(private val googleDocsService: GoogleDocsService) {

    fun generateForRugs(startIndex: String, endIndex: String): Int {
        val spreadSheetDocument = googleDocsService.getRugsDocument(startIndex, endIndex)

        val (startSku, endSku) = getStartEndSku(spreadSheetDocument)
        val csvPrinter = csvPrinter(startSku, endSku)

        for (index in spreadSheetDocument.rows.indices) {
            csvPrinter.printRecord(
                generateModel(index, spreadSheetDocument),
                generateNameWithSizes(index, spreadSheetDocument),
                generateDescription(index, spreadSheetDocument),
                generateCategory(index, spreadSheetDocument),
                generateQuantity(),
                generateMinimum(),
                generateSubtract(),
                generateStockStatus(),
                generateShipping(),
                generateStatus(),
                generateImage(index, spreadSheetDocument, startSku, endSku),
                generateAdditionalImage(index, spreadSheetDocument, startSku, endSku),
                generatePrice(index, spreadSheetDocument),
                generateLength(index, spreadSheetDocument),
                generateWidth(index, spreadSheetDocument),
                generateMetaKeywordForRugs(index, spreadSheetDocument),
                generateMetaTitle(index, spreadSheetDocument),
                generateMetaDescription(index, spreadSheetDocument),
                generateSortOrder(),
                generateSeoKeyword(index, spreadSheetDocument),
                generateDateAvailable(),
                generateSkipImport(),
                generateAttributeWidth(index, spreadSheetDocument),
                generateAttributeLength(index, spreadSheetDocument),
                generateAttributeMaterial(index, spreadSheetDocument),
                generateAttributeWeave(index, spreadSheetDocument),
                generateAttributeStyle(index, spreadSheetDocument),
                generateAttributeColor(index, spreadSheetDocument),
                generateAttributeRegion(index, spreadSheetDocument),
                generateAttributeAge(index, spreadSheetDocument),
                generateAttributeSize(index, spreadSheetDocument),
                generateTagForRugs(index, spreadSheetDocument)
            )
        }
        println("Csv generation successfully finished, ${spreadSheetDocument.rows.size} rugs created!")
        return spreadSheetDocument.rows.size
    }

    fun generateForPillows(startIndex: String, endIndex: String): Int {
        val spreadSheetDocument = googleDocsService.getPillowsDocument(startIndex, endIndex)

        val (startSku, endSku) = getStartEndSku(spreadSheetDocument)
        val csvPrinter = csvPrinter(startSku, endSku)

        for (index in spreadSheetDocument.rows.indices) {
            csvPrinter.printRecord(
                generateModel(index, spreadSheetDocument),
                generateName(index, spreadSheetDocument),
                generateDescription(index, spreadSheetDocument),
                generateCategory(index, spreadSheetDocument),
                generateQuantity(),
                generateMinimum(),
                generateSubtract(),
                generateStockStatus(),
                generateShipping(),
                generateStatus(),
                generateImage(index, spreadSheetDocument, startSku, endSku),
                generateAdditionalImage(index, spreadSheetDocument, startSku, endSku),
                generatePrice(index, spreadSheetDocument),
                generateLength(index, spreadSheetDocument),
                generateWidth(index, spreadSheetDocument),
                generateMetaKeywordForPillows(index, spreadSheetDocument),
                generateMetaTitle(index, spreadSheetDocument),
                generateMetaDescription(index, spreadSheetDocument),
                generateSortOrder(),
                generateSeoKeyword(index, spreadSheetDocument),
                generateDateAvailable(),
                generateSkipImport(),
                generateAttributeWidth(index, spreadSheetDocument),
                generateAttributeLength(index, spreadSheetDocument),
                generateAttributeMaterial(index, spreadSheetDocument),
                generateAttributeWeave(index, spreadSheetDocument),
                generateAttributeStyle(index, spreadSheetDocument),
                generateAttributeColor(index, spreadSheetDocument),
                generateAttributeRegion(index, spreadSheetDocument),
                generateAttributeAge(index, spreadSheetDocument),
                generateTagForPillows(index, spreadSheetDocument)
            )
        }
        println("Csv generation successfully finished, ${spreadSheetDocument.rows.size} pillows created!")
        return spreadSheetDocument.rows.size
    }

    private fun getStartEndSku(spreadSheetDocument: SpreadSheetDocument): Pair<String, String> {
        val startSku = spreadSheetDocument.getCell(0, "SKU")
        val endSku = spreadSheetDocument.getCell(spreadSheetDocument.rows.size - 1, "SKU")
        return Pair(startSku, endSku)
    }

    private fun csvPrinter(startSku: String, endSku: String): CSVPrinter {
        val currentDate = LocalDateTime.now()
        val out =
            FileWriter("${currentDate.format(FORMAT)}-Products($startSku-$endSku).csv")
        val csvPrinter = CSVPrinter(
            out,
            CSVFormat.DEFAULT.withDelimiter(';').withHeader(*CSV_HEADERS_FOR_RUG)
        )
        return csvPrinter
    }
}
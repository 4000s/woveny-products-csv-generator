package com.woveny.wovenyproductsgenerator.service.specials

import com.woveny.wovenyproductsgenerator.constants.MODEL
import com.woveny.wovenyproductsgenerator.constants.PRICE
import com.woveny.wovenyproductsgenerator.domain.SpreadSheetDocument
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.stereotype.Service
import java.io.FileWriter

@Service
class SpecialsService {
    fun generate(spreadSheetDocument: SpreadSheetDocument, fileName: String) {

        val out = FileWriter("$fileName-Specials.csv")
        CSVPrinter(out, CSVFormat.DEFAULT.withDelimiter(';').withHeader(*HEADERS)).use { printer ->
            for (index in spreadSheetDocument.rows.indices) {
                val model = spreadSheetDocument.getCell(index, MODEL)
                val price = spreadSheetDocument.getCell(index, PRICE)
                for (i in 0..5) {
                    val doublePrice = price.toDouble()
                    val discountedPrice =
                        doublePrice - doublePrice * TRADE_DISCOUNT_PERCENTAGES[i] / 100.0
                    printer.printRecord(
                        model,
                        TRADE_PROGRAM_NAMES[i],
                        "1",
                        discountedPrice,
                        "0000-00-00",
                        "0000-00-00"
                    )
                }
            }
        }
        println("Specials csv generation successfully finished, ${spreadSheetDocument.rows.size} specials created!")
    }

    companion object {
        private val HEADERS = arrayOf(
            "model",
            "special:Customer Group",
            "special:Prioirity",
            "special:Price",
            "special:Date Start",
            "special:Date End"
        )
        private val TRADE_PROGRAM_NAMES = arrayOf(
            "Trade 30 Program",
            "Trade 25 Program",
            "Trade 20 Program",
            "Trade 15 Program",
            "Trade 10 Program",
            "Trade Program"
        )
        private val TRADE_DISCOUNT_PERCENTAGES = doubleArrayOf(30.0, 25.0, 20.0, 15.0, 10.0, 10.0)
    }
}
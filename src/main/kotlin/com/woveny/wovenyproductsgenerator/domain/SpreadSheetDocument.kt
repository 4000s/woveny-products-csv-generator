package com.woveny.wovenyproductsgenerator.domain

data class SpreadSheetDocument(val headersMap: Map<String, Int>, val rows: List<List<String>>) {

    fun getCell(rowIndex: Int, columnName: String): String {
        return rows[rowIndex][headersMap.getValue(columnName)]
    }
}
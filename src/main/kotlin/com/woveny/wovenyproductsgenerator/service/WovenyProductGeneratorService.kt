package com.woveny.wovenyproductsgenerator.service

import org.springframework.stereotype.Service

@Service
class WovenyProductGeneratorService(private val googleDocsService: GoogleDocsService) {

    fun generate(startIndex: String, endIndex: String) {
        googleDocsService.getDocumentFor(startIndex, endIndex)
    }


}
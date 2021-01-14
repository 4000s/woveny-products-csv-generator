package com.woveny.wovenyproductsgenerator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "docs.google")
data class GoogleDocsProperties(
    var applicationName: String? = null,
    var rugsSheetName: String? = null,
    var pillowsSheetName: String? = null,
    var rugsDocumentId: String? = null,
    var pillowsDocumentId: String? = null,
    var tokensDirectoryPath: String? = null,
    var credentialsFilePath: String? = null
)
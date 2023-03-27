package com.woveny.wovenyproductsgenerator.service

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory

import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.services.sheets.v4.model.ValueRange
import com.woveny.wovenyproductsgenerator.configuration.GoogleDocsProperties
import com.woveny.wovenyproductsgenerator.domain.SpreadSheetDocument
import org.springframework.stereotype.Service
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

private val JSON_FACTORY: JsonFactory = GsonFactory.getDefaultInstance()
private val HTTP_TRANSPORT: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()
private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS)

@Service
class GoogleDocsService(private val googleDocsProperties: GoogleDocsProperties) {

    private val credentials = getCredentials()

    fun getRugsDocument(startCell: String, endCell: String) = getDocumentFor(
        startCell,
        endCell,
        googleDocsProperties.rugsDocumentId!!,
        googleDocsProperties.rugsSheetName!!
    )

    fun getPillowsDocument(startCell: String, endCell: String) = getDocumentFor(
        startCell,
        endCell,
        googleDocsProperties.pillowsDocumentId!!,
        googleDocsProperties.pillowsSheetName!!
    )

    fun getDocumentFor(startCell: String, endCell: String, documentId: String, sheetName: String): SpreadSheetDocument {
        val ranges =
            listOf("${sheetName}!1:1", "${sheetName}!$startCell:$endCell")

        val service = Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
            .setApplicationName(googleDocsProperties.applicationName).build()

        val valueRanges: MutableList<ValueRange> =
            service.spreadsheets().values()
                .batchGet(documentId)
                .setRanges(ranges).execute().valueRanges

        val keyMap: Map<String, Int> = valueRanges.component1().getValues().first()
            .mapIndexed { index, indexedValue -> indexedValue as String to index }.toMap()

        val dataList = valueRanges.component2().getValues() as List<List<String>>

        return SpreadSheetDocument(keyMap, dataList)
    }

    private fun getCredentials(): Credential {
        val inputStream: InputStream =
            GoogleDocsService::class.java.getResourceAsStream(googleDocsProperties.credentialsFilePath!!)

        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(inputStream))

        val flow: GoogleAuthorizationCodeFlow =
            GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(java.io.File(googleDocsProperties.tokensDirectoryPath!!)))
                .setAccessType("offline")
                .build()
        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }
}

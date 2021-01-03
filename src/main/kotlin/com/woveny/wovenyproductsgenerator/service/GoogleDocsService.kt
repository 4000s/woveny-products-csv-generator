package com.woveny.wovenyproductsgenerator.service

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.services.sheets.v4.model.ValueRange
import com.woveny.wovenyproductsgenerator.domain.SpreadSheetDocument
import org.springframework.stereotype.Service
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

@Service
class GoogleDocsService {
    private val APPLICATION_NAME: String = "Woveny Application"
    private val JSON_FACTORY: JsonFactory = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH: String = "tokens"

    private val DOCUMENT_ID: String = "1KIiXRRMuLP_tcL6EciVBpfB5qUZbqMjXTYiNCHrGri0"
    private val HTTP_TRANSPORT: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()


    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS)
    private val CREDENTIALS_FILE_PATH = "/credentials.json"

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private fun getCredentials(): Credential {
        // Load client secrets.
        val inputStream: InputStream = GoogleDocsService::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)

        val clientSecrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(inputStream))

        // Build flow and trigger user authorization request.
        val flow: GoogleAuthorizationCodeFlow =
            GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build()
        val receiver: LocalServerReceiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }

    fun getDocumentFor(startCell: String, endCell: String): SpreadSheetDocument {
        val ranges = Arrays.asList("Yeni_Ürün!1:1", "Yeni_Ürün!$startCell:$endCell")

        val service = Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
            .setApplicationName(APPLICATION_NAME).build()
        val valueRanges: MutableList<ValueRange> = service.spreadsheets().values().batchGet(DOCUMENT_ID).setRanges(ranges).execute().valueRanges
        val keyMap: Map<String, Int> = valueRanges.first().getValues().first().mapIndexed { index, indexedValue -> indexedValue as String to index }.toMap()
        val dataList = valueRanges.component2().getValues() as List<List<String>>

        return SpreadSheetDocument(keyMap, dataList)
    }

}

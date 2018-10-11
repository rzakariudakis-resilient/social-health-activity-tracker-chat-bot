package com.resilient.neoteric.shatbot.api.services

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import java.io.File


interface GoogleServiceAcountAuthApi{
    fun authorize(): GoogleCredential?
}

class GoogleAuthService {
    companion object CredentialStore: GoogleServiceAcountAuthApi{

        // Set up the HTTP transport and JSON factory
        var httpTransport: HttpTransport = GoogleNetHttpTransport.newTrustedTransport()
        var jsonFactory: JsonFactory = JacksonFactory.getDefaultInstance()

        val serviceAccountScope = "https://www.googleapis.com/auth/chat.bot"
        private var credential: GoogleCredential? = null
        override fun authorize(): GoogleCredential? {
            if (credential == null) {
                credential = buildCredential()
            }
            return credential
        }
        private fun buildCredential(): GoogleCredential? {
            return GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(jsonFactory)
                    .setServiceAccountId("shat-bot@appspot.gserviceaccount.com")
                    .setServiceAccountScopes(listOf(serviceAccountScope))
                    .setServiceAccountPrivateKeyFromP12File(File(GoogleAuthService::class.java!!.getResource("/key.p12").toURI()))
                    .build()
        }
    }

    init {
        authorize()
    }
}
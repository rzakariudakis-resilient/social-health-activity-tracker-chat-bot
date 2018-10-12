package com.resilient.neoteric.shatbot.api.services

import com.google.api.services.chat.v1.HangoutsChat
import com.google.api.services.chat.v1.model.Message
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

interface HangoutsApi {

}

@Service
class HangoutsApiClient: HangoutsApi {
    private val log = LoggerFactory.getLogger(HangoutsApiClient::class.java)

    var APPLICATION_NAME = "shat-bot-api"


    fun sendChat(spaceId: String, messageText: String){
        val hangoutsChat = HangoutsChat.Builder(GoogleAuthService.httpTransport, GoogleAuthService.jsonFactory, GoogleAuthService.authorize()).setApplicationName(APPLICATION_NAME).build()
        hangoutsChat.spaces().messages().create(buildSpaceName(spaceId), Message().setText(messageText)).execute()
        log.debug("sent message $messageText")
    }

    fun listChatMembers(spaceId: String): Int {
        val hangoutsChat = HangoutsChat.Builder(GoogleAuthService.httpTransport, GoogleAuthService.jsonFactory, GoogleAuthService.authorize()).setApplicationName(APPLICATION_NAME).build()
        val listSpacesResponse = hangoutsChat.spaces().members().list(buildSpaceName(spaceId)).execute()
        log.debug("Total members in space ${buildSpaceName(spaceId)}=${listSpacesResponse.memberships.size}")
        return listSpacesResponse.memberships.size
    }

    internal fun buildSpaceName(roomId: String): String{
        return roomId
    }

}
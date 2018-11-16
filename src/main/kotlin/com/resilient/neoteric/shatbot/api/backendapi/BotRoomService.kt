package com.resilient.neoteric.shatbot.api.backendapi

import com.resilient.neoteric.shatbot.api.backendapi.model.BotRoom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class BotRoomService {

    @Autowired
    lateinit var restTemplate: RestTemplate

    fun getBotRoom(): BotRoom {
        val responseEntity: ResponseEntity<BotRoom> = restTemplate.getForEntity("https://bkswz8idxa.execute-api.eu-west-1.amazonaws.com/prod/room", BotRoom::class)
        val roomId = responseEntity.body?.roomId ?: "AAAAQH51DJA"
        return BotRoom(roomId=roomId)
    }
}
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

    fun getBotRoom(): String {
        val responseEntity: ResponseEntity<BotRoom> = restTemplate.getForEntity("https://bkswz8idxa.execute-api.eu-west-1.amazonaws.com/prod/room", String::class)
        return responseEntity.body?.roomId ?: "AAAAQH51DJA"
    }
}
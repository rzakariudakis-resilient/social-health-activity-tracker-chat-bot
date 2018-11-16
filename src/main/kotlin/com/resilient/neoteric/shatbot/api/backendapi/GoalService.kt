package com.resilient.neoteric.shatbot.api.backendapi

import com.resilient.neoteric.shatbot.api.backendapi.model.BotRoom
import com.resilient.neoteric.shatbot.api.backendapi.model.Goal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.math.BigDecimal

@Service
class GoalService {


    @Autowired
    private lateinit var restTemplate: RestTemplate

    fun getCurrentGoalProgress(): Goal{
        val responseEntity: ResponseEntity<Goal> = restTemplate.getForEntity("https://bkswz8idxa.execute-api.eu-west-1.amazonaws.com/prod/goal", Goal::class)
        return responseEntity.body ?: Goal(percentile = BigDecimal.ZERO)
    }

}
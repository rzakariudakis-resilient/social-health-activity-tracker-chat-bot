package com.resilient.neoteric.shatbot.api.controllers

import com.resilient.neoteric.shatbot.api.API_VERSION
import com.resilient.neoteric.shatbot.api.backendapi.BotRoomService
import com.resilient.neoteric.shatbot.api.model.EventDetail
import com.resilient.neoteric.shatbot.api.model.GoalDetail
import com.resilient.neoteric.shatbot.api.services.HangoutsApiClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("$API_VERSION")
class WebhookController {

    @Autowired
    lateinit var botRoomService: BotRoomService

    @Autowired
    lateinit var hangoutsApiClient: HangoutsApiClient

    @PostMapping
    @RequestMapping("pre-event-alert")
    fun notifyUpcomingEvent(@RequestParam("start") startDateTimeMillis: Long){
        hangoutsApiClient.sendChat(botRoomService.getBotRoom(), "@all EventDetail Starting in ${Date(startDateTimeMillis)}")
    }
    @PostMapping
    @RequestMapping("event-start")
    fun notifyEventStart(@RequestBody eventDetail: EventDetail){
        hangoutsApiClient.sendChat(botRoomService.getBotRoom(), "@all Please join us for ${eventDetail.name} starting at ${Date(eventDetail.startDateTimeMillis)}. Click this link ${eventDetail.instructions} for instructions. This needs to be completed by ${Date(eventDetail.endDateTimeMillis)}")
    }
    @PostMapping
    @RequestMapping("event-end")
    fun notifyEventEnd(@RequestBody eventDetail: EventDetail){
        hangoutsApiClient.sendChat(botRoomService.getBotRoom(), "@all ${eventDetail.name} is finished! Well done to all who took part!")

    }
    @PostMapping
    @RequestMapping("goal-achieved")
    fun notifyGoalAchieved(@RequestBody goalDetail: GoalDetail){
        hangoutsApiClient.sendChat(botRoomService.getBotRoom(), "Contratulations @all you have completed another goal!")
    }
}
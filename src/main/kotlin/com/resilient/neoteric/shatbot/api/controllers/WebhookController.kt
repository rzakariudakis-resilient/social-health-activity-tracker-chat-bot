package com.resilient.neoteric.shatbot.api.controllers

import com.resilient.neoteric.shatbot.api.API_VERSION
import com.resilient.neoteric.shatbot.api.backendapi.BotRoomService
import com.resilient.neoteric.shatbot.api.backendapi.GoalService
import com.resilient.neoteric.shatbot.api.backendapi.model.BotRoom
import com.resilient.neoteric.shatbot.api.model.EventDetail
import com.resilient.neoteric.shatbot.api.model.GoalDetail
import com.resilient.neoteric.shatbot.api.services.HangoutsApiClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.postForEntity
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.*

@RestController
@RequestMapping("$API_VERSION")
class WebhookController {
    private val log = LoggerFactory.getLogger(WebhookController::class.java)

    @Autowired
    lateinit var botRoomService: BotRoomService
    @Autowired
    lateinit var goalService: GoalService

    @Autowired
    lateinit var hangoutsApiClient: HangoutsApiClient

    @PostMapping
    @RequestMapping("pre-event-alert")
    fun notifyUpcomingEvent(@RequestParam("start") startDateTimeMillis: Long): ResponseEntity<BotRoom>{
        val botRoom = botRoomService.getBotRoom()
        hangoutsApiClient.sendChat(botRoom.roomId, "Event Coming Soon", "Details to follow...", "EventDetail Starting in <b>${Date(startDateTimeMillis)}</b>")
        val membersCount = hangoutsApiClient.listChatMembers(botRoom.roomId)
        botRoom.totalRoomMembers = membersCount
        log.info("Got room member count")
        return ResponseEntity.ok(botRoom)
    }
    @PostMapping
    @RequestMapping("event-start")
    fun notifyEventStart(@RequestBody eventDetail: EventDetail): ResponseEntity<BotRoom> {
        val botRoom = botRoomService.getBotRoom()
        hangoutsApiClient.sendChat(botRoom.roomId, "Event Started", "Event Detail", "Please join us for <b>${eventDetail.activity.name}</b> starting at ${Date(eventDetail.startDateTimeMillis)}. Click <a href=\"${eventDetail.activity.instructionsUrl}\">here</a> for instructions. This needs to be completed by <b>${Date(eventDetail.endDateTimeMillis)}</b>")
        val membersCount = hangoutsApiClient.listChatMembers(botRoom.roomId)
        botRoom.totalRoomMembers = membersCount
        log.info("Got room member count")
        return ResponseEntity.ok(botRoom)
    }
    @PostMapping
    @RequestMapping("event-end")
    fun notifyEventEnd(@RequestBody eventDetail: EventDetail){
        val goal = goalService.getCurrentGoalProgress()
        val goalProgressPercent = goal.percentile.multiply(BigDecimal.valueOf(100)).round(MathContext(0, RoundingMode.HALF_UP)).toBigInteger().intValueExact()
        val goalMessage = if (goalProgressPercent > 0){
            "You have achieved <b>$goalProgressPercent%</b> of your current goal!"
        } else {
            ""
        }
        hangoutsApiClient.sendChat(botRoomService.getBotRoom().roomId, "Event Finished", "Congratulations", "<b>${eventDetail.activity.name}</b> is finished! Well done to all who took part! $goalMessage")


    }
    @PostMapping
    @RequestMapping("goal-achieved")
    fun notifyGoalAchieved(@RequestBody goalDetail: GoalDetail){
        hangoutsApiClient.sendChat(botRoomService.getBotRoom().roomId, "Goal Achieved!", "Amazing", "<b>Contratulations!</b> you have completed another goal!")
    }
}
package com.resilient.neoteric.shatbot.api

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime


const val SPRING_BASE_SCAN_PACKAGE = "com.resilient.neoteric.shatbot"
const val API_VERSION = "/v1"
@SpringBootApplication(scanBasePackages = [SPRING_BASE_SCAN_PACKAGE])
class BotApiApplication: ApplicationListener<ApplicationReadyEvent> {

    private val logger = LoggerFactory.getLogger(BotApiApplication::class.java)

    override fun onApplicationEvent(p0: ApplicationReadyEvent) {
        logger.info("Started Application ${LocalDateTime.now()}")
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

}
fun main(args: Array<String>) {
    runApplication<BotApiApplication>(*args)
}

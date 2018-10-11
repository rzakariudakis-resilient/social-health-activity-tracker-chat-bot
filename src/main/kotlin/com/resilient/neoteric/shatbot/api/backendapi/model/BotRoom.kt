package com.resilient.neoteric.shatbot.api.backendapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BotRoom(
        @JsonProperty(value="id")
        var roomId: String = ""
)
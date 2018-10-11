package com.resilient.neoteric.shatbot.api.backendapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BotRoom(
        @JsonProperty(value="roomId")
        var roomId: String = "",
        @JsonProperty(value="totalMembers")
        var totalRoomMembers: Int = 0

)
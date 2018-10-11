package com.resilient.neoteric.shatbot.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventDetail(
        @JsonProperty("name")
        var name: String = "",
        @JsonProperty("instructions")
        var instructions: String = "",
        @JsonProperty("start")
        var startDateTimeMillis: Long = 0,
        @JsonProperty("end")
        var endDateTimeMillis: Long = 0
)
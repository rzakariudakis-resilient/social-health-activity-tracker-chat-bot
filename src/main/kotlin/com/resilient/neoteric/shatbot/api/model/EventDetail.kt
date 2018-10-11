package com.resilient.neoteric.shatbot.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class EventDetail(
        @JsonProperty("id")
        var eventId: String = "",
        @JsonProperty("activity")
        var activity: Activity = Activity(),
        @JsonProperty("start")
        var startDateTimeMillis: Long = 0,
        @JsonProperty("end")
        var endDateTimeMillis: Long = 0
)



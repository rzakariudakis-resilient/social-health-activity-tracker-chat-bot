package com.resilient.neoteric.shatbot.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Activity (
        var activityId: String = "",
        @JsonProperty("url")
        var instructionsUrl: String = "",
        @JsonProperty("event_count")
        var eventCount: Int = 0,
        @JsonProperty("name")
        var name: String = ""
)
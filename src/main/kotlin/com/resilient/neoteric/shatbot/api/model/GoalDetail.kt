package com.resilient.neoteric.shatbot.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoalDetail(
        @JsonProperty("year")
        var year: Int = 0,
        @JsonProperty("week")
        var week: Int = 0,
        @JsonProperty("target")
        var target: Int = 0,
        @JsonProperty("current")
        var current: Int = 0,
        @JsonProperty("percentile")
        var percentile: Double = 0.0
)
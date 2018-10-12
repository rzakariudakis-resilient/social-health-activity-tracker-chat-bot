package com.resilient.neoteric.shatbot.api.backendapi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class Goal(
        @JsonProperty(value="year")
        var year: Int = 0,
        @JsonProperty(value="week")
        var week: Int = 0,
        @JsonProperty(value="target")
        var target: Int = 0,
        @JsonProperty(value="current")
        var current: String = "",
        @JsonProperty(value="percentile")
        var percentile: BigDecimal = BigDecimal.ZERO
)
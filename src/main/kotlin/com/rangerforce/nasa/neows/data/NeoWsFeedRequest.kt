package com.rangerforce.nasa.neows.data

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.annotation.Nullable
import jakarta.validation.constraints.NotBlank

@Introspected
@Serdeable
data class NeoWsFeedRequest(
    @field:NotBlank(message = "Start date cannot be blank")
    val startDate: String,

    @field:Nullable
    val endDate: String? = null
)

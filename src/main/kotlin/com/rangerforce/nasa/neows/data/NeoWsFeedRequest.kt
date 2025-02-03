package com.rangerforce.nasa.neows.data

import com.rangerforce.nasa.validations.NullableNotEmpty
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Introspected
@Serdeable
data class NeoWsFeedRequest(
    @field:NotBlank(message = "Start date cannot be blank")
    val startDate: String,

    @field:NullableNotEmpty(message = "End date cannot be empty")
    val endDate: String? = null
)

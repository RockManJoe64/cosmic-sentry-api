package com.rangerforce.hello

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
data class Message(val message: String)

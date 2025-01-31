package com.rangerforce.nasa

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires
import jakarta.validation.constraints.NotBlank

@ConfigurationProperties(NasaApiConfiguration.PREFIX)
@Requires(property = NasaApiConfiguration.PREFIX)
class NasaApiConfiguration {
    @setparam:NotBlank lateinit var key: String // API key
    var neows: NeoWs? = null

    companion object {
        const val PREFIX = "nasa.api"
    }

    // NASA Near Earth Object Web Service (NEOWS)
    @ConfigurationProperties("neows")
    class NeoWs {
        @setparam:NotBlank lateinit var baseUrl: String
        @setparam:NotBlank lateinit var feedPath: String
    }
}

package com.rangerforce.nasa

import com.rangerforce.nasa.neows.data.NearEarthObject
import com.rangerforce.nasa.neows.NeoWsException
import com.rangerforce.nasa.neows.data.NeoWsFeedRequest
import com.rangerforce.nasa.neows.NeoWsService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.hateoas.JsonError
import jakarta.validation.Valid
import kotlinx.datetime.LocalDate
import org.slf4j.LoggerFactory

@Controller("/nasa")
open class NasaApiController(
    private val neoWsService: NeoWsService,
    private val nasaApiConfiguration: NasaApiConfiguration
) {
    private val log = LoggerFactory.getLogger(NasaApiController::class.java)

    @Post(
        value="/neows/feed",
        processes = [MediaType.APPLICATION_JSON]
    )
    open suspend fun getNearEarthObjects(@Body @Valid request: NeoWsFeedRequest): List<NearEarthObject>? {
        val startDate = LocalDate.parse(request.startDate)
        val endDate = request.endDate?.let {
            if (it.isBlank()) {
                null
            } else {
                LocalDate.parse(it)
            }
        }
        return neoWsService.fetchNearEarthObjects(startDate, endDate, nasaApiConfiguration.key)
    }

    @Error(NeoWsException::class)
    fun neoWsError(request: HttpRequest<*>, e: NeoWsException): HttpResponse<JsonError> {
        log.error("Error fetching data: ${e.message}", e)

        val error = JsonError("Error fetching data from the Near Earth Object Web Service.")

        return HttpResponse.serverError<JsonError>()
            .body(error)
    }

    @Error
    fun error(request: HttpRequest<*>, e: Throwable): HttpResponse<JsonError> {
        log.error("Something bad happened: ${e.message}", e)

        val error = JsonError("An error occurred. Check the logs.")

        return HttpResponse.serverError<JsonError>()
            .body(error) // (3)
    }
}

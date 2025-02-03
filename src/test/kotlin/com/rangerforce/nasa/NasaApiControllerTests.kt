package com.rangerforce.nasa

import com.rangerforce.nasa.neows.NeoWsException
import com.rangerforce.nasa.neows.NeoWsService
import com.rangerforce.nasa.neows.data.NearEarthObject
import com.rangerforce.nasa.neows.data.NeoWsFeedRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.hateoas.JsonError
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import jakarta.validation.ConstraintViolationException

@MicronautTest
class NasaApiControllerTests : StringSpec({

    val neoWsService = mockk<NeoWsService>()
    val nasaApiConfiguration = mockk<NasaApiConfiguration>()
    val controller = NasaApiController(neoWsService, nasaApiConfiguration)

    listOf(
        NeoWsFeedRequest("2023-01-01", "2023-01-02"),
        NeoWsFeedRequest("2023-01-01", ""),
        NeoWsFeedRequest("2023-01-01", null),
    ).forEach({ request ->
        "should return near earth objects for request: $request" {
            // Arrange
            every { nasaApiConfiguration.key } returns "DEMO_KEY"
            val expectedResponse = listOf<NearEarthObject>() // Mocked response
            coEvery { neoWsService.fetchNearEarthObjects(any(), any(), any()) } returns expectedResponse

            // Act
            val response = controller.getNearEarthObjects(request)

            // Assert
            response shouldBe expectedResponse
        }
    })

    // Micronaut framework will handle the validation error and return a 400 Bad Request response
    "!should handle validation error for empty date value" {
        // Arrange
        val request = NeoWsFeedRequest("", "2023-01-02")

        // Act & Assert
        try {
            controller.getNearEarthObjects(request)
        } catch (e: ConstraintViolationException) {
            e.message shouldBe "Start date must not be blank"
        }
    }

    "should handle NeoWsException" {
        // Arrange
        val request = HttpRequest.GET<Any>("/nasa/neows/feed")
        val exception = NeoWsException("Error", Throwable())

        // Act
        val response = controller.neoWsError(request, exception)

        // Assert
        response.status shouldBe HttpResponse.serverError<JsonError>().status
        response.body().message shouldBe "Error fetching data from the Near Earth Object Web Service."
    }

    "should handle generic errors" {
        // Arrange
        val request = HttpRequest.GET<Any>("/nasa/neows/feed")
        val exception = RuntimeException("Something bad happened")

        // Act
        val response = controller.error(request, exception)

        // Assert
        response.status shouldBe HttpResponse.serverError<JsonError>().status
        response.body().message shouldBe "An error occurred. Check the logs."
    }
})

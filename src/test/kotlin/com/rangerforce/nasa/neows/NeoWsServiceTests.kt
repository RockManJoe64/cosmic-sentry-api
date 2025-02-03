package com.rangerforce.nasa.neows

import com.rangerforce.nasa.neows.data.NearEarthObject
import com.rangerforce.nasa.neows.data.NeoWsFeed
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.datetime.LocalDate

class NeoWsServiceTests : StringSpec({

    val neoWsClient = mockk<NeoWsClient>()
    val service = NeoWsService(neoWsClient)

    "should fetch near earth objects successfully" {
        // Arrange
        val startDate = LocalDate.parse("2023-01-01")
        val endDate = LocalDate.parse("2023-01-02")
        val apiKey = "DEMO_KEY"
        val expectedResponse = listOf<NearEarthObject>() // Mocked response
        coEvery { neoWsClient.fetchFeed(any(), any(), any()) } returns
                NeoWsFeed(2, mapOf(
                    "2023-01-01" to listOf(),
                    "2023-01-02" to listOf()
                ))

        // Act
        val result = service.fetchNearEarthObjects(startDate, endDate, apiKey)

        // Assert
        result shouldBe expectedResponse
    }

    "should handle exception when fetching near earth objects" {
        // Arrange
        val startDate = LocalDate.parse("2023-01-01")
        val endDate = LocalDate.parse("2023-01-02")
        val apiKey = "DEMO_KEY"
        coEvery { neoWsClient.fetchFeed(any(), any(), any()) } throws Exception("Error")

        // Act & Assert
        try {
            service.fetchNearEarthObjects(startDate, endDate, apiKey)
        } catch (e: NeoWsException) {
            e.message shouldBe "Error fetching NEO WS feed: Error"
        }
    }

    "should use start date as end date if end date is null" {
        // Arrange
        val startDate = LocalDate.parse("2023-01-01")
        val apiKey = "DEMO_KEY"
        val expectedResponse = listOf<NearEarthObject>() // Mocked response
        coEvery { neoWsClient.fetchFeed(any(), any(), any()) } returns NeoWsFeed(1, mapOf("2023-01-01" to expectedResponse))

        // Act
        val result = service.fetchNearEarthObjects(startDate, null, apiKey)

        // Assert
        result shouldBe expectedResponse
    }
})

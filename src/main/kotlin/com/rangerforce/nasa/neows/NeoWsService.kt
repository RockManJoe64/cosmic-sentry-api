package com.rangerforce.nasa.neows

import jakarta.inject.Singleton
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format

@Singleton
class NeoWsService(private val neoWsClient: NeoWsClient) {

    suspend fun fetchNearEarthObjects(startDate: LocalDate, endDate: LocalDate?, apiKey: String): List<NearEarthObject>? {
        val startDateString = startDate.format(LocalDate.Formats.ISO)
        val endDateString = endDate?.format(LocalDate.Formats.ISO) ?: startDate.format(LocalDate.Formats.ISO)
        return try {
            val response = neoWsClient.fetchFeed(startDateString, endDateString, apiKey)
            response.near_earth_objects.values.flatten()
        } catch (e: Exception) {
            throw NeoWsException("Error fetching NEO WS feed: ${e.message}", e)
        }
    }
}

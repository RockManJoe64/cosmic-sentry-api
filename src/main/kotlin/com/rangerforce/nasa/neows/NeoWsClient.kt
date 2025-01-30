package com.rangerforce.nasa.neows

import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("nasa")
interface NeoWsClient {

    @Get("/neo/rest/v1/feed?start_date={startDate}&end_date={endDate}&apiKey={apiKey}")
    @SingleResult
    suspend fun fetchFeed(startDate: String, endDate: String, apiKey: String): NeoWsFeed
}

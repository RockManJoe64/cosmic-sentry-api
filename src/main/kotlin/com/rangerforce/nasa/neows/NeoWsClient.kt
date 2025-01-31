package com.rangerforce.nasa.neows

import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

//@Client(id="neows", path="\${nasa.api.neows.base-url}")
@Client("\${nasa.api.neows.base-url}")
interface NeoWsClient {

    @Get("\${nasa.api.neows.feed-path}?start_date={startDate}&end_date={endDate}&api_key={apiKey}")
    @SingleResult
    suspend fun fetchFeed(startDate: String, endDate: String, apiKey: String): NeoWsFeed
}

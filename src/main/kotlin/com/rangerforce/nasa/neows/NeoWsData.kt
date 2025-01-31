package com.rangerforce.nasa.neows

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.SerialName

@Serdeable
data class NeoWsFeed(
    val element_count: Int,
    val near_earth_objects: Map<String, List<NearEarthObject>>
)

@Serdeable
data class NearEarthObject(
    val links: Links,
    val id: String,
    val neo_reference_id: String,
    val name: String,
    val nasa_jpl_url: String,
    val absolute_magnitude_h: Double,
    val estimated_diameter: EstimatedDiameter,
    val is_potentially_hazardous_asteroid: Boolean,
    val close_approach_data: List<CloseApproachData>,
    val is_sentry_object: Boolean
)

@Serdeable
data class Links(
    val self: String
)

@Serdeable
data class EstimatedDiameter(
    val kilometers: DiameterRange,
    val meters: DiameterRange,
    val miles: DiameterRange,
    val feet: DiameterRange
)

@Serdeable
data class DiameterRange(
    @SerialName("estimated_diameter_min")  val estimatedDiameterMin: Double,
    @SerialName("estimated_diameter_max") val estimatedDiameterMax: Double
)

@Serdeable
data class CloseApproachData(
    val close_approach_date: String,
    val close_approach_date_full: String,
    val epoch_date_close_approach: Long,
    val relative_velocity: RelativeVelocity,
    val miss_distance: MissDistance,
    val orbiting_body: String
)

@Serdeable
data class RelativeVelocity(
    val kilometers_per_second: String,
    val kilometers_per_hour: String,
    val miles_per_hour: String
)

@Serdeable
data class MissDistance(
    val astronomical: String,
    val lunar: String,
    val kilometers: String,
    val miles: String
)

package pe.com.test.data.network.dto

import com.google.gson.annotations.SerializedName
import pe.com.test.data.network.dto.MovieUpcoming

class MovieUpcomingBase(
    val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<MovieUpcoming>
)
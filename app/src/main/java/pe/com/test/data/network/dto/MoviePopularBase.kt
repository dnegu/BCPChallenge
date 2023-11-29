package pe.com.test.data.network.dto

import com.google.gson.annotations.SerializedName

data class MoviePopularBase(
    val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<MoviePopular>
)
package pe.com.test.data.network.service

import pe.com.test.data.network.dto.MoviePopularBase
import pe.com.test.data.network.dto.MovieUpcomingBase
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("3/movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MoviePopularBase

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language: String
    ): MovieUpcomingBase
}
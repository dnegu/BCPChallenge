package pe.com.test.data.network.repositories

import pe.com.test.data.network.dto.MoviePopularBase
import pe.com.test.data.network.dto.MovieUpcomingBase
import pe.com.test.data.network.service.MovieService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) : MovieService{
    override suspend fun popularMovies(
        apiKey: String,
        page: String,
        language: String
    ): MoviePopularBase {
        return movieService.popularMovies(
            apiKey,
            page,
            language
        )
    }

    override suspend fun upcomingMovies(
        apiKey: String,
        page: String,
        language: String
    ): MovieUpcomingBase {
        return movieService.upcomingMovies(
            apiKey,
            page,
            language
        )
    }
}
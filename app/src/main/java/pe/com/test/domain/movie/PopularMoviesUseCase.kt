package pe.com.test.domain.movie

import pe.com.test.data.network.service.MovieService
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val repository: MovieService) {
    suspend operator fun invoke(
        apiKey: String,
        page: String,
        language: String
    ) = repository.popularMovies(apiKey,page, language)
}
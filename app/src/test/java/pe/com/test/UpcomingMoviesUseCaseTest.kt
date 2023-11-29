package pe.com.test

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import pe.com.test.data.network.dto.MovieUpcoming
import pe.com.test.data.network.dto.MovieUpcomingBase
import pe.com.test.data.network.service.MovieService
import pe.com.test.domain.movie.UpcomingMoviesUseCase


class UpcomingMoviesUseCaseTest {

    @Mock
    private lateinit var mockRepository: MovieService

    private lateinit var upcomingMoviesUseCase: UpcomingMoviesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        upcomingMoviesUseCase = UpcomingMoviesUseCase(mockRepository)
    }

    @Test
    fun `invoke should return popular movies`() = runBlocking {
        // Arrange
        val apiKey = "your_api_key"
        val page = "1"
        val language = "en"
        val expectedMovies = MovieUpcomingBase(
            page = 1,
            totalResults = 1,
            totalPages = 5,
            results = listOf(
                MovieUpcoming(4.5,40,true,"path",1,false,"","","", listOf(),"",4.5,"","")
            )
        )

        `when`(mockRepository.upcomingMovies(apiKey, page, language)).thenReturn(expectedMovies)

        // Act
        val result = upcomingMoviesUseCase(apiKey, page, language)

        // Assert
        assertEquals(expectedMovies, result)
    }

    // Add more test cases as needed

    @Test
    fun `invoke should return zero movies`() = runBlocking {
        // Arrange
        val apiKey = "your_api_key"
        val page = "1"
        val language = "en"
        val expectedMovies = MovieUpcomingBase(
            page = 1,
            totalResults = 1,
            totalPages = 5,
            results = listOf()
        )

        `when`(mockRepository.upcomingMovies(apiKey, page, language)).thenReturn(expectedMovies)

        // Act
        val result = upcomingMoviesUseCase(apiKey, page, language)

        // Assert
        assertEquals(0, result.results.size)
    }
}
package pe.com.test.ui.home.views.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import pe.com.test.common.launch
import pe.com.test.domain.movie.PopularMoviesUseCase
import pe.com.test.domain.movie.UpcomingMoviesUseCase
import pe.com.test.ui.home.objects.HomeObject
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: PopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: UpcomingMoviesUseCase
) : ViewModel() {

    private var pagePopular = 1
    private var pageUpcoming = 1
    private var language = "en-US"
    private val homeObject = HomeObject

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    private val _movieList = MutableSharedFlow<MovieListUIEvent>()
    val movieList get() = _movieList.asSharedFlow()

    private fun getPopularMovies() = launch{
        _movieList.emit(MovieListUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPopularMoviesUseCase(homeObject.API_KEY_MOVIES, pagePopular.toString(), language)
            }.onSuccess { moviePopularBase ->
                _movieList.emit(MovieListUIEvent.SuccessPopular(moviePopularBase.results))
                _movieList.emit(MovieListUIEvent.HideLoading)
            }.onFailure {
                _movieList.emit(MovieListUIEvent.Error(it.message.toString()))
                _movieList.emit(MovieListUIEvent.HideLoading)
            }
        }
    }

    private fun getUpcomingMovies() = launch{
        _movieList.emit(MovieListUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getUpcomingMoviesUseCase(homeObject.API_KEY_MOVIES, pageUpcoming.toString(), language)
            }.onSuccess { movieUpcomingBase ->
                _movieList.emit(MovieListUIEvent.SuccessUpcoming(movieUpcomingBase.results))
                _movieList.emit(MovieListUIEvent.HideLoading)
            }.onFailure {
                _movieList.emit(MovieListUIEvent.Error(it.message.toString()))
                _movieList.emit(MovieListUIEvent.HideLoading)
            }
        }
    }
}
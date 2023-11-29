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
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: PopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: UpcomingMoviesUseCase
) : ViewModel() {

    init {
        getPopularMovies()
        getUpcomingMovies()
    }

    private val _movieList = MutableSharedFlow<MovieListUIEvent>()
    val movieList get() = _movieList.asSharedFlow()

    fun getPopularMovies() = launch{
        _movieList.emit(MovieListUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getPopularMoviesUseCase("d9ae4921794c06bd0fdbd1463d274804", "1", "en-US")
            }.onSuccess { moviePopularBase ->
                _movieList.emit(MovieListUIEvent.SuccessPopular(moviePopularBase.results))
                _movieList.emit(MovieListUIEvent.HideLoading)
            }.onFailure {
                _movieList.emit(MovieListUIEvent.Error(it.message.toString()))
                _movieList.emit(MovieListUIEvent.HideLoading)
            }
        }
    }

    fun getUpcomingMovies() = launch{
        _movieList.emit(MovieListUIEvent.ShowLoading)
        viewModelScope.launch {
            runCatching {
                getUpcomingMoviesUseCase("d9ae4921794c06bd0fdbd1463d274804", "1", "en-US")
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
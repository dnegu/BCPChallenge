package pe.com.test.ui.home.views.movie_list

import pe.com.test.data.network.dto.MoviePopular
import pe.com.test.data.network.dto.MovieUpcoming

sealed class MovieListUIEvent{
    object ShowLoading : MovieListUIEvent()
    object HideLoading : MovieListUIEvent()
    data class Error(val error: String) : MovieListUIEvent()
    data class SuccessPopular(val list :List<MoviePopular>) : MovieListUIEvent()
    data class SuccessUpcoming(val list :List<MovieUpcoming>) : MovieListUIEvent()
}

package pe.com.test.ui.home.views.movie_detail

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
class MovieDetailViewModel @Inject constructor(
) : ViewModel() {


}
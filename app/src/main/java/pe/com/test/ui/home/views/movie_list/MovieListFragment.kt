package pe.com.test.ui.home.views.movie_list

import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pe.com.test.common.BaseFragment
import pe.com.test.databinding.FragmentFirstBinding
import pe.com.test.ui.home.HomeActivity
import pe.com.test.ui.home.adapters.AdapterMovieUpcoming
import pe.com.test.ui.home.adapters.MoviePopularAdapter

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding,MovieListViewModel>() {

    var adapterUpcoming = AdapterMovieUpcoming()
    lateinit var moviePopularAdapter: MoviePopularAdapter
    override fun getViewModelClass() = MovieListViewModel::class.java

    override fun getViewBinding() = FragmentFirstBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieList.flowWithLifecycle(lifecycle).collectLatest { event ->
                when(event){
                    MovieListUIEvent.ShowLoading -> {
                        (requireActivity() as HomeActivity).showLoading(true)
                    }
                    MovieListUIEvent.HideLoading -> {
                        (requireActivity() as HomeActivity).showLoading(false)
                    }
                    is MovieListUIEvent.Error -> {
                        Snackbar.make(binding!!.baseView, event.error, Snackbar.LENGTH_LONG).show()
                    }
                    is MovieListUIEvent.SuccessPopular -> {
                        moviePopularAdapter = MoviePopularAdapter(event.list)
                        binding.moviePopularRecyclerView.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        binding.moviePopularRecyclerView!!.adapter = moviePopularAdapter
                    }
                    is MovieListUIEvent.SuccessUpcoming -> {
                        adapterUpcoming.data = event.list
                        binding?.movieUpcomingRecyclerView?.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        binding?.movieUpcomingRecyclerView!!.adapter = adapterUpcoming
                    }
                }
            }
        }

    }
}
package pe.com.test.ui.home.views.movie_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import pe.com.test.ui.home.adapters.AdapterMovieUpcoming
import pe.com.test.ui.home.adapters.MoviePopularAdapter
import pe.com.test.ui.home.MyViewModel

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding,MovieListViewModel>() {

    var adapterUpcoming = AdapterMovieUpcoming()
    lateinit var moviePopularAdapter: MoviePopularAdapter
    override fun getViewModelClass() = MovieListViewModel::class.java

    override fun getViewBinding() = FragmentFirstBinding.inflate(layoutInflater)

/*override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {




    view_model!!.da.observe(viewLifecycleOwner) { user ->
        moviePopularAdapter = MoviePopularAdapter(user)
        binding?.moviePopularRecyclerView?.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding?.moviePopularRecyclerView!!.adapter = moviePopularAdapter
    }

    view_model!!.error.observe(viewLifecycleOwner) { error ->
        Snackbar.make(binding!!.baseView, error, Snackbar.LENGTH_LONG).show()
    }

    view_model!!.movieUpcoming.observe(viewLifecycleOwner) { follow ->
        adapterUpcoming.data = follow
        binding?.movieUpcomingRecyclerView?.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding?.movieUpcomingRecyclerView!!.adapter = adapterUpcoming
    }

    view_model!!.data()

    return binding?.root
}*/

    override fun setUpViews() {
        super.setUpViews()

        viewModel.getUpcomingMovies()
        viewModel.getPopularMovies()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieList.flowWithLifecycle(lifecycle).collectLatest { event ->
                when(event){
                    MovieListUIEvent.ShowLoading -> {

                    }
                    MovieListUIEvent.HideLoading -> {

                    }
                    MovieListUIEvent.Error -> {

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
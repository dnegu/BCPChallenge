package pe.com.test.ui.home.views.movie_detail

import com.bumptech.glide.Glide
import pe.com.test.common.BaseFragment
import pe.com.test.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding,MovieDetailViewModel>() {


    override fun getViewModelClass() = MovieDetailViewModel::class.java

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun setUpViews(){
        super.setUpViews()
        with(binding){
            textViewName.text = arguments?.getString("title")
            textViewDescription.text = arguments?.getString("overview")

            val imageURL = "https://image.tmdb.org/t/p/w185/${arguments?.getString("posterPath")}"
            Glide.with(requireContext())
                .load(imageURL)
                .into(imageViewPoster)
        }
    }
}
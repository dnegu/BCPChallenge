package pe.com.test.ui.home.views.movie_detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import pe.com.test.common.BaseFragment
import pe.com.test.databinding.FragmentDetailBinding
import java.util.concurrent.Executors

class DetailFragment : BaseFragment<FragmentDetailBinding,MovieDetailViewModel>() {


    override fun getViewModelClass() = MovieDetailViewModel::class.java

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun setUpViews() = with(binding) {
        super.setUpViews()

        textViewName.text = arguments?.getString("title")
        textViewDescription.text = arguments?.getString("overview")

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap?
        executor.execute {
            val imageURL = "https://image.tmdb.org/t/p/w185/${arguments?.getString("posterPath")}"
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    imageViewPoster.setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
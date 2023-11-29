package pe.com.test.ui.home.views.movie_detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import pe.com.test.R
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
        var image: Bitmap? = null
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
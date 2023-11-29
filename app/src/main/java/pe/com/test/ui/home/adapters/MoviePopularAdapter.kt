package pe.com.test.ui.home.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pe.com.test.R
import pe.com.test.data.network.dto.MoviePopular
import java.net.URL
import java.util.concurrent.Executors

class MoviePopularAdapter(val moviePopular: List<MoviePopular?>) :
    RecyclerView.Adapter<MoviePopularAdapter.MoviePopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePopularViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_popular, parent, false)
        return MoviePopularViewHolder(view)
    }

    override fun getItemCount(): Int = moviePopular.size

    override fun onBindViewHolder(holder: MoviePopularViewHolder, position: Int) {
        val item = moviePopular[position]
        holder.bind(item!!)
        val bundle = bundleOf("title" to item.title, "posterPath" to item.posterPath, "overview" to item.overview)
        holder.itemView.setOnClickListener{ view ->

            view.findNavController().navigate(R.id.action_FirstFragment_to_DetailFragment, bundle)
        }
    }

    class MoviePopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(moviePopular: MoviePopular) {
            val imageView = itemView.findViewById<ImageView>(R.id.posterImageView)
            val executor = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())
            var image: Bitmap?
            executor.execute {
                val imageURL = "https://image.tmdb.org/t/p/w185/${moviePopular.posterPath}"
                try {
                    val `in` = URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    handler.post {
                        imageView.setImageBitmap(image)
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
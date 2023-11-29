package pe.com.test.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.com.test.R
import pe.com.test.data.network.dto.MovieUpcoming
import pe.com.test.databinding.MovieUpcomingItemBinding

class AdapterMovieUpcoming(private var data: List<MovieUpcoming?>) :
    RecyclerView.Adapter<AdapterMovieUpcoming.MovieUpcomingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieUpcomingViewHolder {
        val binding = MovieUpcomingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieUpcomingViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieUpcomingViewHolder, position: Int) {
        val item = data[position]
        item?.let { holder.bind(it) }
    }

    fun updateData(newData: List<MovieUpcoming?>) {
        data = newData
        notifyDataSetChanged()
    }

    class MovieUpcomingViewHolder(private val binding: MovieUpcomingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieUpcoming: MovieUpcoming) {

            binding.root.setOnClickListener {
                val bundle = bundleOf(
                    "title" to movieUpcoming.title,
                    "posterPath" to movieUpcoming.posterPath,
                    "overview" to movieUpcoming.overview
                )

                it.findNavController().navigate(R.id.action_FirstFragment_to_DetailFragment, bundle)
            }

            // Utilizar Glide o Picasso para cargar imágenes en lugar de la lógica manual
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w185/${movieUpcoming.posterPath}")
                .into(binding.posterImageView)
        }
    }
}
package pe.com.test.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.com.test.R
import pe.com.test.data.network.dto.MoviePopular
import pe.com.test.databinding.ItemMoviePopularBinding

class MoviePopularAdapter(private val moviePopular: List<MoviePopular?>) :
    RecyclerView.Adapter<MoviePopularAdapter.MoviePopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePopularViewHolder {
        val binding = ItemMoviePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviePopularViewHolder(binding)
    }

    override fun getItemCount(): Int = moviePopular.size

    override fun onBindViewHolder(holder: MoviePopularViewHolder, position: Int) {
        val item = moviePopular[position]
        item?.let { holder.bind(it) }
    }

    class MoviePopularViewHolder(private val binding: ItemMoviePopularBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(moviePopular: MoviePopular) {

            binding.root.setOnClickListener {
                val bundle = bundleOf(
                    "title" to moviePopular.title,
                    "posterPath" to moviePopular.posterPath,
                    "overview" to moviePopular.overview
                )

                it.findNavController().navigate(R.id.action_FirstFragment_to_DetailFragment, bundle)
            }

            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w185/${moviePopular.posterPath}")
                .into(binding.posterImageView)
        }
    }
}
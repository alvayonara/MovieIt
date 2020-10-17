package com.alvayonara.movieit.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase
import com.alvayonara.movieit.data.Resource

class DetailMovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        this.movieId.value = movieId
    }

    var movieDetail: LiveData<Resource<Movie>> =
        Transformations.switchMap(movieId) {
            catalogueUseCase.getMovieById(it)
        }

    fun setFavoriteMovie() {
        val movieDetailResource = movieDetail.value
        if (movieDetailResource != null) {
            val movie = movieDetailResource.data

            if (movie != null) {
                val newState = !movie.favored

                catalogueUseCase.setMovieFavorite(movie, newState)
            }
        }
    }
}
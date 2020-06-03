package com.alvayonara.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        this.movieId.value = movieId
    }

    var movieDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { mMovieId ->
            catalogueRepository.getMovieById(mMovieId)
        }

    fun setFavoriteMovie() {
        val movieDetailResource = movieDetail.value
        if (movieDetailResource != null) {
            val movieEntity = movieDetailResource.data

            if (movieEntity != null) {
                val newState = !movieEntity.favored

                catalogueRepository.setMovieFavorite(movieEntity, newState)
            }
        }
    }
}
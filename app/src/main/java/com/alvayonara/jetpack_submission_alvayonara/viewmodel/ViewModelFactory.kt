package com.alvayonara.jetpack_submission_alvayonara.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.jetpack_submission_alvayonara.data.source.CatalogueRepository
import com.alvayonara.jetpack_submission_alvayonara.di.Injection
import com.alvayonara.jetpack_submission_alvayonara.ui.movie.DetailMovieViewModel
import com.alvayonara.jetpack_submission_alvayonara.ui.movie.MovieViewModel
import com.alvayonara.jetpack_submission_alvayonara.ui.tvshow.DetailTvShowViewModel
import com.alvayonara.jetpack_submission_alvayonara.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                return DetailTvShowViewModel(catalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
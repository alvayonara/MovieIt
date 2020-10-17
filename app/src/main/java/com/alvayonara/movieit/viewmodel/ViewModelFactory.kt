package com.alvayonara.movieit.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alvayonara.movieit.data.CatalogueRepository
import com.alvayonara.movieit.di.Injection
import com.alvayonara.movieit.domain.usecase.CatalogueUseCase
import com.alvayonara.movieit.ui.favorite.movie.FavoriteMovieViewModel
import com.alvayonara.movieit.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.alvayonara.movieit.ui.home.HomeViewModel
import com.alvayonara.movieit.ui.movie.DetailMovieViewModel
import com.alvayonara.movieit.ui.movie.MovieViewModel
import com.alvayonara.movieit.ui.search.SearchViewModel
import com.alvayonara.movieit.ui.tvshow.DetailTvShowViewModel
import com.alvayonara.movieit.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val catalogueUseCase: CatalogueUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideCatalogueUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(
                    catalogueUseCase
                ) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(catalogueUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
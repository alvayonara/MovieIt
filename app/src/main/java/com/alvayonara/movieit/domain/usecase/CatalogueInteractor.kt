package com.alvayonara.movieit.domain.usecase

import androidx.lifecycle.LiveData
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.domain.repository.ICatalogueRepository
import com.alvayonara.movieit.vo.Resource

class CatalogueInteractor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {

    override fun getAllMovies(): LiveData<Resource<List<Movie>>> =
        catalogueRepository.getAllMovies()

    override fun getMovieById(movieId: String): LiveData<Resource<Movie>> =
        catalogueRepository.getMovieById(movieId)

    override fun getFavoredMovies(): LiveData<List<Movie>> = catalogueRepository.getFavoredMovies()

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        catalogueRepository.setMovieFavorite(movie, state)

    override fun getAllTvShows(): LiveData<Resource<List<TvShow>>> =
        catalogueRepository.getAllTvShows()

    override fun getTvShowById(tvShowId: String): LiveData<Resource<TvShow>> =
        catalogueRepository.getTvShowById(tvShowId)

    override fun getFavoredTvShows(): LiveData<List<TvShow>> =
        catalogueRepository.getFavoredTvShows()

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) =
        catalogueRepository.setTvShowFavorite(tvShow, state)

    override fun getMovieSearch(query: String): LiveData<Resource<List<Movie>>> =
        catalogueRepository.getMovieSearch(query)
}
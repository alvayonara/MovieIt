package com.alvayonara.movieit.domain.usecase

import com.alvayonara.movieit.data.Resource
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.domain.repository.ICatalogueRepository
import io.reactivex.Flowable

class CatalogueInteractor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {

    override fun getAllMovies(): Flowable<Resource<List<Movie>>> =
        catalogueRepository.getAllMovies()

    override fun getMovieById(movieId: String): Flowable<Resource<Movie>> =
        catalogueRepository.getMovieById(movieId)

    override fun getFavoredMovies(): Flowable<List<Movie>> = catalogueRepository.getFavoredMovies()

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        catalogueRepository.setMovieFavorite(movie, state)

    override fun getAllTvShows(): Flowable<Resource<List<TvShow>>> =
        catalogueRepository.getAllTvShows()

    override fun getTvShowById(tvShowId: String): Flowable<Resource<TvShow>> =
        catalogueRepository.getTvShowById(tvShowId)

    override fun getFavoredTvShows(): Flowable<List<TvShow>> =
        catalogueRepository.getFavoredTvShows()

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) =
        catalogueRepository.setTvShowFavorite(tvShow, state)

    override fun getMovieSearch(query: String): Flowable<Resource<List<Movie>>> =
        catalogueRepository.getMovieSearch(query)
}
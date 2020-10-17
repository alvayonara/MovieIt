package com.alvayonara.movieit.utils

import com.alvayonara.movieit.data.source.local.entity.MovieEntity
import com.alvayonara.movieit.data.source.local.entity.TvShowEntity
import com.alvayonara.movieit.data.source.remote.response.MovieResponse
import com.alvayonara.movieit.data.source.remote.response.TvShowResponse
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                popularity = it.popularity,
                averageVote = it.averageVote,
                favored = false,
                search = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieResponseToEntity(input: MovieResponse) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        popularity = input.popularity,
        averageVote = input.averageVote,
        favored = false,
        // CHECK ! SEARCH-> TRUE
        search = false
    )

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                popularity = it.popularity,
                averageVote = it.averageVote,
                favored = it.favored,
                search = it.search
            )
        }

    fun mapMovieEntityToDomain(input: MovieEntity) = Movie(
        movieId = input.movieId,
        title = input.title,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        popularity = input.popularity,
        averageVote = input.averageVote,
        favored = input.favored,
        search = input.search
    )

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        popularity = input.popularity,
        averageVote = input.averageVote,
        favored = input.favored,
        search = input.search
    )


    fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                tvShowId = it.tvShowId,
                title = it.title,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                popularity = it.popularity,
                averageVote = it.averageVote,
                favored = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvShowResponseToEntity(input: TvShowResponse) = TvShowEntity(
        tvShowId = input.tvShowId,
        title = input.title,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        popularity = input.popularity,
        averageVote = input.averageVote,
        favored = false
    )

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                tvShowId = it.tvShowId,
                title = it.title,
                originalTitle = it.originalTitle,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                popularity = it.popularity,
                averageVote = it.averageVote,
                favored = it.favored
            )
        }

    fun mapTvShowEntityToDomain(input: TvShowEntity) = TvShow(
        tvShowId = input.tvShowId,
        title = input.title,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        popularity = input.popularity,
        averageVote = input.averageVote,
        favored = input.favored
    )

    fun mapTvShowDomainToEntity(input: TvShow) = TvShowEntity(
        tvShowId = input.tvShowId,
        title = input.title,
        originalTitle = input.originalTitle,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        popularity = input.popularity,
        averageVote = input.averageVote,
        favored = input.favored
    )
}
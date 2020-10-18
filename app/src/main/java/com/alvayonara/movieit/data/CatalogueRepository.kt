package com.alvayonara.movieit.data

import com.alvayonara.movieit.data.source.local.LocalDataSource
import com.alvayonara.movieit.data.source.remote.RemoteDataSource
import com.alvayonara.movieit.data.source.remote.network.ApiResponse
import com.alvayonara.movieit.data.source.remote.response.MovieResponse
import com.alvayonara.movieit.data.source.remote.response.TvShowResponse
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.domain.repository.ICatalogueRepository
import com.alvayonara.movieit.utils.AppExecutors
import com.alvayonara.movieit.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    ICatalogueRepository {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogueRepository =
            instance
                ?: synchronized(this) {
                    instance
                        ?: CatalogueRepository(
                            remoteData, localData, appExecutors
                        )
                }
    }

    override fun getAllMovies(): Flowable<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flowable<List<Movie>> =
                localDataSource.getAllMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getMovieById(movieId: String): Flowable<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flowable<Movie> = localDataSource.getMovieById(movieId)
                .map { DataMapper.mapMovieEntityToDomain(it) }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override fun createCall(): Flowable<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieById(movieId)

            override fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapMovieResponseToEntity(data)
                localDataSource.updateMovie(movie)
            }
        }.asFlowable()
    }

    override fun getFavoredMovies(): Flowable<List<Movie>> =
        localDataSource.getFavoredMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }

    override fun getAllTvShows(): Flowable<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flowable<List<TvShow>> =
                localDataSource.getAllTvShows().map { DataMapper.mapTvShowEntitiesToDomain(it) }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShows(tvShowList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getTvShowById(tvShowId: String): Flowable<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowResponse>() {
            override fun loadFromDB(): Flowable<TvShow> =
                localDataSource.getTvShowById(tvShowId)
                    .map { DataMapper.mapTvShowEntityToDomain(it) }

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override fun createCall(): Flowable<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowById(tvShowId)

            override fun saveCallResult(data: TvShowResponse) {
                val tvShow = DataMapper.mapTvShowResponseToEntity(data)
                localDataSource.updateTvShow(tvShow)
            }
        }.asFlowable()
    }

    override fun getFavoredTvShows(): Flowable<List<TvShow>> =
        localDataSource.getFavoredTvShows().map { DataMapper.mapTvShowEntitiesToDomain(it) }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShowEntity, state) }
    }

    // NEED FIX
    // FIX THIS FUNCTION (SEARCH) LATER
    override fun getMovieSearch(query: String): Flowable<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flowable<List<Movie>> =
                localDataSource.getMovieSearch(query)
                    .map { DataMapper.mapMovieEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                true

            override fun createCall(): Flowable<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieSearch(query)

            override fun saveCallResult(data: List<MovieResponse>) {
            }
        }.asFlowable()
    }
}
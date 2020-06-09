package com.alvayonara.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alvayonara.moviecatalogue.data.source.local.LocalDataSource
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.data.source.remote.ApiResponse
import com.alvayonara.moviecatalogue.data.source.remote.RemoteDataSource
import com.alvayonara.moviecatalogue.data.source.remote.response.MovieResponse
import com.alvayonara.moviecatalogue.data.source.remote.response.TvShowResponse
import com.alvayonara.moviecatalogue.utils.AppExecutors
import com.alvayonara.moviecatalogue.vo.Resource

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogueDataSource {

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

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.movieId,
                        response.title,
                        response.originalTitle,
                        response.originalLanguage,
                        response.posterPath,
                        response.overview,
                        response.releaseDate,
                        response.voteCount,
                        response.popularity,
                        response.averageVote
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieById(movieId)

            override fun saveCallResult(data: MovieResponse) {
                val movie = MovieEntity(
                    data.movieId,
                    data.title,
                    data.originalTitle,
                    data.originalLanguage,
                    data.posterPath,
                    data.overview,
                    data.releaseDate,
                    data.voteCount,
                    data.popularity,
                    data.averageVote,
                    false,
                    search = true
                )
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getFavoredMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoredMovies(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.tvShowId,
                        response.title,
                        response.originalTitle,
                        response.originalLanguage,
                        response.posterPath,
                        response.overview,
                        response.releaseDate,
                        response.voteCount,
                        response.popularity,
                        response.averageVote
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowById(tvShowId)

            override fun saveCallResult(data: TvShowResponse) {}
        }.asLiveData()
    }

    override fun getFavoredTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoredTvShows(), config).build()
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }

    override fun getMovieSearch(query: String): LiveData<Resource<List<MovieEntity>>> {
        return object :
            NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localDataSource.getMovieSearch(query)

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieSearch(query)

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.movieId,
                        response.title,
                        response.originalTitle,
                        response.originalLanguage,
                        response.posterPath,
                        response.overview,
                        response.releaseDate,
                        response.voteCount,
                        response.popularity,
                        response.averageVote,
                        false,
                        search = true
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }
}
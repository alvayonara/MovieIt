package com.alvayonara.movieit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alvayonara.movieit.data.source.local.LocalDataSource
import com.alvayonara.movieit.data.source.remote.ApiResponse
import com.alvayonara.movieit.data.source.remote.RemoteDataSource
import com.alvayonara.movieit.data.source.remote.response.MovieResponse
import com.alvayonara.movieit.data.source.remote.response.TvShowResponse
import com.alvayonara.movieit.domain.model.Movie
import com.alvayonara.movieit.domain.model.TvShow
import com.alvayonara.movieit.domain.repository.ICatalogueRepository
import com.alvayonara.movieit.utils.AppExecutors
import com.alvayonara.movieit.utils.DataMapper
import com.alvayonara.movieit.vo.Resource

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

    override fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getAllMovies()) {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieById(movieId: String): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<Movie> {
                return Transformations.map(localDataSource.getMovieById(movieId)) {
                    DataMapper.mapMovieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieById(movieId)

            override fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapMovieResponseToEntity(data)
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getFavoredMovies(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoredMovies()) {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }

    override fun getAllTvShows(): LiveData<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResource<List<TvShow>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShow>> {
                return Transformations.map(localDataSource.getAllTvShows()) {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: String): LiveData<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShow> {
                return Transformations.map(localDataSource.getTvShowById(tvShowId)) {
                    DataMapper.mapTvShowEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowById(tvShowId)

            override fun saveCallResult(data: TvShowResponse) {
                val tvShow = DataMapper.mapTvShowResponseToEntity(data)
                localDataSource.updateTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoredTvShows(): LiveData<List<TvShow>> {
        return Transformations.map(localDataSource.getFavoredTvShows()) {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
    }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShowEntity, state) }
    }

    // NEED FIX
    // FIX THIS FUNCTION (SEARCH) LATER
    override fun getMovieSearch(query: String): LiveData<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getMovieSearch(query)) {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                true

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieSearch(query)

            override fun saveCallResult(data: List<MovieResponse>) {
            }
        }.asLiveData()
    }
}
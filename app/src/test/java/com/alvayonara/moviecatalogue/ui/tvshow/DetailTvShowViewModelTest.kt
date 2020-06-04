package com.alvayonara.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alvayonara.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alvayonara.moviecatalogue.data.CatalogueRepository
import com.alvayonara.moviecatalogue.utils.FakeDataDummy
import com.alvayonara.moviecatalogue.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = FakeDataDummy.generateRemoteDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(catalogueRepository)
        viewModel.setSelectedTvShow(tvShowId!!)
    }

    @Test
    fun getTvShow() {
        val dummyTvShowDetail = Resource.success(dummyTvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShowDetail

        `when`(catalogueRepository.getTvShowById(tvShowId)).thenReturn(tvShow)

        viewModel.tvShowDetail.observeForever(tvShowObserver)

        verify(tvShowObserver).onChanged(dummyTvShowDetail)
    }
}
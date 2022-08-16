package com.aks.nasapictures.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aks.nasapictures.ui.datasource.repo.IPictureListRepo
import com.aks.nasapictures.ui.utils.StatusHandler
import com.aks.nasapictures.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PictureListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var pictureViewModel: PictureListViewModel

    @MockK
    private lateinit var pictureRepo: IPictureListRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        pictureViewModel = PictureListViewModel(pictureRepo)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Success when data parsed successfully`() = runTest {
        coEvery { pictureRepo.getPictureData() } returns StatusHandler.Success(
            FakeData.data
        )

        val dispacher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(dispacher)

        pictureViewModel.sendRequestToGetPictureList()

        assertEquals(
            StatusHandler.Success(FakeData.data),
            pictureViewModel.getListOfPictures.getOrAwaitValue()
        )
    }

}
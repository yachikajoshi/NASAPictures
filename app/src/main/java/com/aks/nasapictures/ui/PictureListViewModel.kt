package com.aks.nasapictures.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aks.nasapictures.datasource.model.PictureData
import com.aks.nasapictures.datasource.repo.IPictureListRepo
import com.aks.nasapictures.utils.StatusHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureListViewModel @Inject constructor(private val pictureListRepo: IPictureListRepo) :
    ViewModel() {

    private val pictureListData = MutableLiveData<StatusHandler<List<PictureData>>>()

    val getListOfPictures: LiveData<StatusHandler<List<PictureData>>>
        get() = pictureListData

    fun sendRequestToGetPictureList() {
        viewModelScope.launch {

            pictureListData.value = pictureListRepo.getPictureData()

        }
    }
}
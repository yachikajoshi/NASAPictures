package com.aks.nasapictures.ui.datasource.repo

import com.aks.nasapictures.ui.datasource.model.PictureData
import com.aks.nasapictures.ui.utils.StatusHandler

interface IPictureListRepo {
    suspend fun getPictureData(): StatusHandler<List<PictureData>>
}
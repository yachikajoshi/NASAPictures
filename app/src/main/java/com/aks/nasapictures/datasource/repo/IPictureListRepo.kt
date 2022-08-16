package com.aks.nasapictures.datasource.repo

import com.aks.nasapictures.datasource.model.PictureData
import com.aks.nasapictures.utils.StatusHandler

interface IPictureListRepo {
    suspend fun getPictureData(): StatusHandler<List<PictureData>>
}
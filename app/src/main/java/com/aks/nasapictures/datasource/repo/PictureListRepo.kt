package com.aks.nasapictures.datasource.repo

import android.content.Context
import com.aks.nasapictures.datasource.model.PictureData
import com.aks.nasapictures.utils.StatusHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class PictureListRepo @Inject constructor(@ApplicationContext var context: Context) :
    IPictureListRepo {

    override suspend fun getPictureData(): StatusHandler<List<PictureData>> {
        return try {
            val jsonString =
                context.resources.assets.open("data.json").bufferedReader()
                    .use { it.readText() }
            val listPictureDataType = object : TypeToken<List<PictureData>>() {}.type
            StatusHandler.Success(Gson().fromJson(jsonString, listPictureDataType))
        } catch (ioException: IOException) {
            StatusHandler.Error(ioException)
        }
    }

}
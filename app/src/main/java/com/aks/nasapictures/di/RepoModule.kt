package com.aks.nasapictures.di

import android.content.Context
import com.aks.nasapictures.datasource.repo.IPictureListRepo
import com.aks.nasapictures.datasource.repo.PictureListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepoModule {

    @Provides
    @ViewModelScoped
    fun providePictureRepo(@ApplicationContext context: Context): IPictureListRepo {
        return PictureListRepo(context)
    }
}
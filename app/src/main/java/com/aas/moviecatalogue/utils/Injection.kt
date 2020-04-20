package com.aas.moviecatalogue.utils

import android.app.Application
import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.local.LocalRepository
import com.aas.moviecatalogue.data.source.remote.RemoteRepository

object Injection {
    fun provideRepository(application: Application): MainRepository {
        val remoteRepository = RemoteRepository()
        val localRepository = LocalRepository(application)
        val appExecutors = AppExecutors()
        return MainRepository(remoteRepository, localRepository, appExecutors)
    }
}
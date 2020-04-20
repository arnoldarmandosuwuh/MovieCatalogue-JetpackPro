package com.aas.moviecatalogue.utils

import com.aas.moviecatalogue.data.source.MainRepository
import com.aas.moviecatalogue.data.source.remote.RemoteRepository

object Injection {
    fun provideRepository(): MainRepository {
        val remoteRepository = RemoteRepository()
        return MainRepository(remoteRepository)
    }
}
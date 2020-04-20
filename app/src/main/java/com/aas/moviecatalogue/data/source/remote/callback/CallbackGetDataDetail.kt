package com.aas.moviecatalogue.data.source.remote.callback

import retrofit2.Call

interface CallbackGetDataDetail<T, I> {
    fun onSuccess(data: T)
    fun onFailed(call: Call<I>, e: Throwable)
}
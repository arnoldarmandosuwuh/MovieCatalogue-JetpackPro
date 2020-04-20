package com.aas.moviecatalogue.data.source.remote.callback

import retrofit2.Call

interface CallbackGetData<T, I> {
    fun onSuccess(data: List<T>)
    fun onFailed(call: Call<I>, e: Throwable)

}
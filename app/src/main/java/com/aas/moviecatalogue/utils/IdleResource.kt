package com.aas.moviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdleResource {

    private const val resource = "RESOURCE"
    private val espressoTestIdlingResource = CountingIdlingResource(resource)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun getEsspressoIdlingResource(): IdlingResource = espressoTestIdlingResource
}
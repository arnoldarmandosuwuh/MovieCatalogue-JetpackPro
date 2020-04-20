package com.aas.moviecatalogue.utils

import android.view.View

const val TAG = "MovieCatalogue"

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}
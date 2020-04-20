package com.aas.moviecatalogue.util

import com.aas.moviecatalogue.utils.AppExecutors
import java.util.concurrent.Executor

open class AppExecutor : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor {
            it.run()
        }
    }
}
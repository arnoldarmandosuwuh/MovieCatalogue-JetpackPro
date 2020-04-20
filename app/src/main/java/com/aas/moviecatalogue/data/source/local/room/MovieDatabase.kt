package com.aas.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private const val databaseName = "movie_catalogue.db"
        private lateinit var INSTANCE: MovieDatabase

        fun getDatabase(context: Context): MovieDatabase {
            synchronized(MovieDatabase::class.java) {
                INSTANCE =
                    Room.databaseBuilder(context, MovieDatabase::class.java, databaseName).build()
            }
            return INSTANCE
        }
    }
}
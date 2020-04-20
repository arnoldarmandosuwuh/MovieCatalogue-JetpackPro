package com.aas.moviecatalogue.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.aas.moviecatalogue.data.source.local.entity.MovieEntity
import com.aas.moviecatalogue.data.source.local.entity.TvShowEntity
import com.aas.moviecatalogue.data.source.local.map.MovieMap
import com.aas.moviecatalogue.data.source.local.map.TvShowMap

@Dao
interface MovieDao {

    @WorkerThread
    @Query("SELECT * FROM ${MovieMap.tableName}")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @WorkerThread
    @Query("SELECT * FROM ${MovieMap.tableName} WHERE ${MovieMap.favorite} = 1")
    fun getFavoriteMovieAsPaged(): DataSource.Factory<Int, MovieEntity>

    @WorkerThread
    @Query("SELECT * FROM ${TvShowMap.tableName}")
    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>>

    @WorkerThread
    @Query("SELECT * FROM ${TvShowMap.tableName} WHERE ${TvShowMap.favorite} = 1")
    fun getFavoriteTvShowAsPaged(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(data: List<MovieEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(data: MovieEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(data: List<TvShowEntity>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(data: TvShowEntity): Int
}
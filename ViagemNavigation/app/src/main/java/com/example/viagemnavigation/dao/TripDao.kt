package com.example.viagemnavigation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.viagemnavigation.model.Trip
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Insert
    fun insert(trip: Trip): Long

    @Update
    fun update(trip: Trip)

    @Upsert
    suspend fun upsert(trip: Trip): Long

    @Query("select * from trip order by trip.startDate")
    fun getAll(): Flow<List<Trip>>

    @Query("select * from trip where trip.id = :id")
    fun findById(id: Long) : Trip?

    @Delete
    suspend fun delete(trip: Trip)
}
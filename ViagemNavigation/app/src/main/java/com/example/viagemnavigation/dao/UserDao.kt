package com.example.viagemnavigation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.viagemnavigation.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Upsert
    suspend fun upsert(user: User): Long

    @Query("select * from user order by user.username")
    fun getAll(): Flow<List<User>>

    @Query("select * from user where user.id = :id")
    suspend fun findById(id: Long) : User?

    @Delete
    fun delete(user: User)
}
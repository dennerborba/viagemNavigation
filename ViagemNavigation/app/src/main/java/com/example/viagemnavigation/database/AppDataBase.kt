package com.example.viagemnavigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.viagemnavigation.dao.TripDao
import com.example.viagemnavigation.dao.UserDao
import com.example.viagemnavigation.model.Trip
import com.example.viagemnavigation.model.User

@Database(entities = [User::class, Trip::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract val userDao : UserDao
    abstract val tripDao : TripDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context, AppDataBase::class.java,
                "user-db1"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
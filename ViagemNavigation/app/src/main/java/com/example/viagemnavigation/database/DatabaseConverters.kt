package com.example.viagemnavigation.database

import androidx.room.TypeConverter
import com.example.viagemnavigation.model.TripType
import java.util.Date

class DatabaseConverters {

    @TypeConverter
    fun intToTripType(value: Int?): TripType? {
        return TripType.values().firstOrNull() { it.ordinal == value}
    }

    @TypeConverter
    fun tripTypeToInt(type: TripType): Int {
        return type.ordinal
    }


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
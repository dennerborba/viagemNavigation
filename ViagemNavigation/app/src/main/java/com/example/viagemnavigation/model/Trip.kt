package com.example.viagemnavigation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class Trip(
    @PrimaryKey (autoGenerate = true)
    val id: Long = 0,
    var destination: String,
    var type: TripType,
    var startDate: Date,
    var endDate: Date,
    var value: Double
)
enum class TripType {
    LAZER, NEGOCIOS
}
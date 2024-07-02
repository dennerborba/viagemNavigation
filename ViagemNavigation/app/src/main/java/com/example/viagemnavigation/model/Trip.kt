package com.example.viagemnavigation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
@Entity
data class Trip(
    @PrimaryKey (autoGenerate = true)
    val id: Long = 0,
    val destination: String = "",
    val type: TripType = TripType.LAZER,
    val startDate: Date = Date(),
    val endDate: Date = Date(),
    val value: Double = 0.0
)
enum class TripType {
    LAZER, NEGOCIOS
}
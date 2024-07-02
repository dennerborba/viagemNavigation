package com.example.viagemnavigation.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.room.TypeConverter
import com.example.viagemnavigation.dao.TripDao
import com.example.viagemnavigation.database.DatabaseConverters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date


class TripViewModel (val tripDao: TripDao): ViewModel() {
    private val _uiState = MutableStateFlow(Trip())
    val uiState: StateFlow<Trip> = _uiState.asStateFlow()

    fun updateDestination(destination: String) {
        _uiState.update { it.copy(destination = destination) }
    }

    fun updateTripType(databaseConverters: DatabaseConverters) {
        _uiState.update { it.copy(type = databaseConverters.intToTripType()) }
    }

    fun updateStartDate(startDate: Date) {
        _uiState.update { it.copy(startDate = startDate) }
    }



}

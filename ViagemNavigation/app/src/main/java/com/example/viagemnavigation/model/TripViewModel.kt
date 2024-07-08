package com.example.viagemnavigation.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.TypeConverter
import com.example.viagemnavigation.dao.TripDao
import com.example.viagemnavigation.database.AppDataBase
import com.example.viagemnavigation.database.DatabaseConverters
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date
class TripViewModelFactory(val db: AppDataBase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return TripViewModel(db.tripDao) as T
    }
}

class TripViewModel (val tripDao: TripDao): ViewModel() {
    private val _uiState = MutableStateFlow(Trip())
    val uiState: StateFlow<Trip> = _uiState.asStateFlow()

    fun updateDestination(destination: String) {
        _uiState.update { it.copy(destination = destination) }
    }

    fun updateTripType(tripType: TripType) {
        _uiState.update { it.copy(type = tripType) }
    }

    fun updateStartDate(startDate: Date) {
        _uiState.update { it.copy(startDate = startDate) }
    }

    fun updateEndDate(endDate: Date) {
        _uiState.update { it.copy(endDate = endDate) }
    }

    fun updateValue(value: Double) {
        _uiState.update { it.copy(value = value) }
    }

    private fun updateId(id: Long){
        _uiState.update { it.copy(id = id) }
    }

    fun save() {
        viewModelScope.launch {
            val id = tripDao.upsert(uiState.value)
            if (id > 0) {
                updateId(id)
            }
        }
    }

    fun deleteTrip(trip: Trip){
        viewModelScope.launch {
            tripDao.delete(trip)
        }
    }

    fun editTrip(id: Long){
        viewModelScope.launch {
            val trip = tripDao.findById(id)
            _uiState.value = trip ?: Trip()
        }
    }

    private fun new(){
        _uiState.update{
            val copy = it.copy(id = 0,
                type = TripType.LAZER,
                destination = "",
                startDate = Date(),
                endDate = Date(),
                value = 0.0
                )
            copy
        }
    }

    fun saveNew(){
        save()
        new()
    }
    fun getAll() = tripDao.getAll()

    suspend fun findById(id: Long): Trip?{
        val deferred: Deferred<Trip?> = viewModelScope.async {
            tripDao.findById(id)
        }
        return deferred.await()
    }

}



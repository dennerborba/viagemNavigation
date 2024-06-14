package com.example.viagemnavigation.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


class TripViewModel : ViewModel(){
    private val savedTrip = mutableStateListOf<Trip>()

    fun saveTrip(trip: Trip){
        savedTrip.add(trip)
    }
    fun getSavedTrip(): List<Trip> {
        return savedTrip.toList()
    }
}

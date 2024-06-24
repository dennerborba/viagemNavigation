package com.example.viagemnavigation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String = "",
    val email: String = "",
    val password: String = ""
){
}
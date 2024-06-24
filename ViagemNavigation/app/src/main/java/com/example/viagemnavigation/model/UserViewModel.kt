package com.example.viagemnavigation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viagemnavigation.dao.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(val userDao: UserDao): ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()


    fun updateUsername(username: String){
        _uiState.update {
            it.copy(username = username)
        }
    }
    fun updateEmail(email: String){
        _uiState.update {
            it.copy(email = email)
        }
    }
    fun updatePassword(password: String){
        _uiState.update {
            it.copy(password = password)
        }
    }
    private fun updateId(id: Long){
        _uiState.update {
            it.copy(id = id)
        }
    }
    private fun new(){
        _uiState.update {
            it.copy(id = 0, username = "", email = "", password = "")
        }
    }
    fun save() {
        viewModelScope.launch {
            val id = userDao.upsert(uiState.value)
            if (id > 0){
                updateId(id)
            }
        }
    }
    fun saveNew(){
        save()
        new()
    }
}
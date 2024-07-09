package com.example.viagemnavigation.model

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.viagemnavigation.dao.UserDao
import com.example.viagemnavigation.database.AppDataBase
import com.example.viagemnavigation.screens.toast
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModelFactory(val db: AppDataBase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return UserViewModel(db.userDao) as T
    }
}

class UserViewModel(val userDao: UserDao) : ViewModel() {
    private val _uiState = MutableStateFlow(User())
    val uiState: StateFlow<User> = _uiState.asStateFlow()


    fun updateUsername(username: String) {
        _uiState.update {
            it.copy(username = username)
        }
    }

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    private fun updateId(id: Long) {
        _uiState.update {
            it.copy(id = id)
        }
    }

    private fun new() {
        _uiState.update {
            it.copy(id = 0, username = "", email = "", password = "")
        }
    }

    fun save(context: Context) {
        viewModelScope.launch {
            val usernameExists = userDao.checkUsernameExists(uiState.value.username)
            if (usernameExists > 0) {
                context.toast("Este nome de usuário já existe.")
            } else if (uiState.value.email.isEmpty() || uiState.value.username.isEmpty() || uiState.value.password.isEmpty()) {
                context.toast("Por favor, insira os dados faltantes.")
            } else {
                val id = userDao.upsert(uiState.value)
                if (id > 0) {
                    updateId(id)
                    context.toast("Usuário cadastrado com sucesso!")
                }
            }
        }
    }

    suspend fun login(username: String, password: String): User? {
        val deferred: Deferred<User?> = viewModelScope.async {
            userDao.findByUserPassword(username, password)
        }
        return deferred.await()
    }

    suspend fun findById(id: Long): User? {
        val deferred: Deferred<User?> = viewModelScope.async {
            userDao.findById(id)
        }
        return deferred.await()
    }

}
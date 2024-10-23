package com.example.lab4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4.model.User
import com.example.lab4.model.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    fun registerUser(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val flag = userService.registerUser(user)
            if(flag) onSuccess() else onError("Ошибка: Имя пользователя уже занято")
        }
    }

    fun loginUser(login: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val user = userService.loginUser(login, password)
            if(user != null) onSuccess(user.username)
            else onError("Ошибка: Неверный логин или пароль")
        }
    }

    suspend fun updateUser(user: User) = withContext(Dispatchers.IO) {
        userService.updateUser(user)
    }

    suspend fun deleteUser(user: User) = withContext(Dispatchers.IO) {
        userService.deleteUser(user)
    }
}
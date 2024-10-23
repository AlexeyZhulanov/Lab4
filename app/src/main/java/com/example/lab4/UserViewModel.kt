package com.example.lab4

import androidx.lifecycle.ViewModel
import com.example.lab4.model.User
import com.example.lab4.model.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    suspend fun registerUser(user: User): Boolean = withContext(Dispatchers.IO) {
        return@withContext userService.registerUser(user)
    }
}
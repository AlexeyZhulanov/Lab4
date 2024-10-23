package com.example.lab4.model

import android.util.Log
import com.example.lab4.room.UserDao
import com.example.lab4.room.UserDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun registerUser(user: User) = withContext(Dispatchers.IO) {
        return@withContext try {
            userDao.registerUser(UserDbEntity.fromUserInput(user))
            true
        } catch (e: Exception) {
            Log.e("RegisterUserDbException", e.message.toString())
            false
        }
    }

    override suspend fun loginUser(login: String, password: String): User? = withContext(Dispatchers.IO) {
        return@withContext userDao.loginUser(login, password)?.toUser()
    }

    override suspend fun updateUser(user: User): Unit = withContext(Dispatchers.IO) {
        try {
            userDao.updateUser(UserDbEntity.fromUserInput(user))
        } catch (e: Exception) {
            Log.e("UpdateUserDbException", e.message.toString())
        }
    }

    override suspend fun deleteUser(user: User): Unit = withContext(Dispatchers.IO) {
        try {
            userDao.deleteUser(UserDbEntity.fromUserInput(user))
        } catch (e:Exception) {
            Log.e("DeleteUserDbException", e.message.toString())
        }
    }
}
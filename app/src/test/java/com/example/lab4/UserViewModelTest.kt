package com.example.lab4

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.lab4.model.UserService
import com.example.lab4.room.UserDao
import com.example.lab4.model.User

// TDD Register test
class UserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userService: UserService
    private lateinit var userViewModel: UserViewModel
    private val userDao: UserDao = mockk()

    @Before
    fun setUp() {
        userService = UserService(userDao)
        userViewModel = UserViewModel(userService)
    }

    @Test
    fun `registerUser should return true on successful registration`() = runTest {
        // Arrange
        val user = User(0,"login", "password", "Alexey")
        coEvery { userDao.registerUser(any()) } returns Unit

        // Act
        val result = userViewModel.registerUser(user)

        // Assert
        assertTrue(result)
        coVerify { userDao.registerUser(any()) }
    }

    @Test
    fun `registerUser should return false on registration failure`() = runTest {
        // Arrange
        val user = User(0,"login", "password", "Alexey")
        coEvery { userDao.registerUser(any()) } throws Exception()

        // Act
        val result = userViewModel.registerUser(user)

        // Assert
        assertFalse(result)
        coVerify { userDao.registerUser(any()) }
    }
}

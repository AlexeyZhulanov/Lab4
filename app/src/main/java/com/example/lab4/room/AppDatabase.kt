package com.example.lab4.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        UserDbEntity::class
    ]
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
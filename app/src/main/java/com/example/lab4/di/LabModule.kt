package com.example.lab4.di

import android.content.Context
import androidx.room.Room
import com.example.lab4.model.AndroidLogger
import com.example.lab4.model.Logger
import com.example.lab4.model.UserService
import com.example.lab4.room.AppDatabase
import com.example.lab4.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LabModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "database.db"
        ).createFromAsset("init_db_lab4.db").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideUserService(userDao: UserDao, logger: Logger) : UserService {
        return UserService(userDao, logger)
    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = AndroidLogger()
}
package com.hilt.di

import com.hilt.data.response.Toyota
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideCar(): Toyota {
        return Toyota()
    }
}
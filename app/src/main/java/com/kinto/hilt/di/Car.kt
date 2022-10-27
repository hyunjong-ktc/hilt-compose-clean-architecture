package com.kinto.hilt.di

import com.kinto.hilt.Car
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CarModule {

    @Provides
    fun provideCar(): Car {
        return Car()
    }
}
package com.hilt.di

import android.content.Context
import android.content.SharedPreferences
import com.hilt.PrismPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalStorageModule {

    @PrismPrefs
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("com.kinto.hilt", Context.MODE_PRIVATE)
    }

}
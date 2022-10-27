package com.hilt.di

import com.apollographql.apollo3.ApolloClient
import com.hilt.PrismApolloClient
import com.hilt.repository.SignUpRepositoryImpl
import com.hilt.repository.UserRepositoryImpl
import com.kinto.domain.repository.SignUpRepository
import com.kinto.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Provides
    fun provideSignUpRepository(@PrismApolloClient apolloClient: ApolloClient): SignUpRepository {
        return SignUpRepositoryImpl(apolloClient)
    }
}
package com.hilt.data.repository

import com.apollographql.apollo3.ApolloClient
import com.hilt.data.RegisterAccountMutation
import com.hilt.data.remote.PrismApolloClient
import com.hilt.data.toModel
import com.kinto.domain.model.SignUpEmailModel
import com.kinto.domain.repository.SignUpRepository
import dagger.Provides
import javax.inject.Inject

class SignUpRepositoryImpl(@PrismApolloClient val apolloClient: ApolloClient) :
    SignUpRepository {

    override suspend fun doSignUp(email: String): SignUpEmailModel {
        return apolloClient.mutation(RegisterAccountMutation(email)).execute().toModel()
    }
}
package com.hilt.repository

import com.apollographql.apollo3.ApolloClient
import com.hilt.PrismApolloClient
import com.hilt.data.RegisterAccountMutation
import com.hilt.toModel
import com.kinto.domain.model.SignUpEmailModel
import com.kinto.domain.repository.SignUpRepository

class SignUpRepositoryImpl(@PrismApolloClient val apolloClient: ApolloClient) :
    SignUpRepository {

    override suspend fun doSignUp(email: String): SignUpEmailModel {
        return apolloClient.mutation(RegisterAccountMutation(email)).execute().toModel()
    }
}
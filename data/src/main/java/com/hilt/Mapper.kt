package com.hilt

import com.apollographql.apollo3.api.ApolloResponse
import com.hilt.data.RegisterAccountMutation
import com.kinto.domain.model.SignUpEmailModel

fun ApolloResponse<RegisterAccountMutation.Data>.toModel(): SignUpEmailModel {
    val isExisting = this.data?.registerAccount?.registerAccountStatus ?: false
    val message = this.data?.registerAccount?.registerAccountMessage ?: ""
    return SignUpEmailModel(isExisting = isExisting, message = message)
}

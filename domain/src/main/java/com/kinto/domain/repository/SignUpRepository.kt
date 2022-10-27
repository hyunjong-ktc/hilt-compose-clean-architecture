package com.kinto.domain.repository

import com.kinto.domain.model.SignUpEmailModel

interface SignUpRepository {

    suspend fun doSignUp(email: String): SignUpEmailModel
}
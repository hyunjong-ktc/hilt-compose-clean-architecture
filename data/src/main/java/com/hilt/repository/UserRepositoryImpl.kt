package com.hilt.repository

import com.kinto.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun get(): String {
        return "PRISM>>>>>>>>>>"
    }

}
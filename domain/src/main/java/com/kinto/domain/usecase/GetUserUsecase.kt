package com.kinto.domain.usecase

import com.kinto.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository) :
    NoParameterUseCase<String> {

    override fun get(): String {
        return repository.get()
    }

}
package com.kinto.domain.usecase

import com.kinto.domain.model.SignUpEmailModel
import com.kinto.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(val repository: SignUpRepository) :
    SingleParameterUseCase<String, SignUpEmailModel> {

    override suspend fun get(param: String): SignUpEmailModel {
        return repository.doSignUp(param)
    }
}
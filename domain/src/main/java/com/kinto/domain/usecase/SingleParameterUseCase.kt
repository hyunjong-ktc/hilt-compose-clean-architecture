package com.kinto.domain.usecase

interface SingleParameterUseCase<P, T> {
   suspend fun get(param: P): T
}
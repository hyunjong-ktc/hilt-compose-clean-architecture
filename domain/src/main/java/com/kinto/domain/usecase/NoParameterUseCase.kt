package com.kinto.domain.usecase

interface NoParameterUseCase<T> {
    fun get(): T
}
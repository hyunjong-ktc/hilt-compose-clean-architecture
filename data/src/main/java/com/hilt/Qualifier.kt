package com.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismApolloClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BearerToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismPrefs
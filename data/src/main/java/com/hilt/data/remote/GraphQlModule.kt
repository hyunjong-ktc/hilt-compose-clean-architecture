package com.hilt.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.hilt.const.NetworkConst.HEADER_LABEL_AUTHORIZATION
import com.hilt.const.NetworkConst.HEADER_LABEL_VERSION
import com.hilt.const.NetworkConst.HEADER_LABEL_VERSION_UP_LATER
import com.hilt.const.NetworkConst.HEADER_VERSION
import com.hilt.const.NetworkConst.HEADER_VERSION_UP_LATER
import com.hilt.const.NetworkConst.SERVER_URL_DEBUG
import com.hilt.const.NetworkConst.SERVER_URL_PRODUCTION
import com.hilt.const.NetworkConst.SERVER_URL_STAGING
import com.hilt.data.BuildConfig
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GraphQlModule {

    @PrismApolloClient
    @Singleton
    @Provides
    fun provideApolloClient(@PrismOkHttpClient okHttpClient: OkHttpClient): ApolloClient {
        //TODO BuildConfig.BUILD_TYPE and BuildConfig.Flavorで分岐させる
        val serverUrl = when (BuildConfig.BUILD_TYPE) {
            "debug" -> {
                SERVER_URL_DEBUG
            }
            "staging" -> {
                SERVER_URL_STAGING
            }
            else -> {
                SERVER_URL_PRODUCTION
            }
        }

        return ApolloClient.Builder()
            .serverUrl(serverUrl)
            .okHttpClient(okHttpClient)
            .build()
    }

    @PrismOkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClient(@PrismInterceptor authorizationInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @PrismInterceptor
    @Singleton
    @Provides
    fun provideAuthorizationInterceptor(): Interceptor {
        return AuthorizationInterceptor("Bearer ")
    }

    class AuthorizationInterceptor constructor(val bearerToken: String = "") :
        Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader(HEADER_LABEL_AUTHORIZATION, bearerToken)
                .addHeader(HEADER_LABEL_VERSION, HEADER_VERSION)
                .addHeader(HEADER_LABEL_VERSION_UP_LATER, HEADER_VERSION_UP_LATER)
                .build()
            return chain.proceed(request)
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PrismApolloClient
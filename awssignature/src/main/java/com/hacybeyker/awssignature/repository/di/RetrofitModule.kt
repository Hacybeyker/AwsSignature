package com.hacybeyker.awssignature.repository.di

import com.hacybeyker.awssignature.repository.services.ICognitoServices
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Carlos Osorio on 11/03/2021
 */

val retrofitModule = module {
    single { providerGsonConverterFactory() }
    single { providerOkHttpClient() }
    single { providerRetrofit("https://cognito-identity.us-east-2.amazonaws.com", get(), get()) }
    single { providerCognitoServices(get()) }
}

fun providerGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun providerOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .build()
}

fun providerRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun providerCognitoServices(retrofit: Retrofit): ICognitoServices {
    return retrofit.create(ICognitoServices::class.java)
}
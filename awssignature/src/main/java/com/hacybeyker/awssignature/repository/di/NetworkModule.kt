package com.hacybeyker.awssignature.repository.di

import com.hacybeyker.awssignature.repository.implement.CognitoNetwork
import com.hacybeyker.awssignature.usecases.repository.ICognitoRepositoryNetwork
import org.koin.dsl.module

/**
 * Created by Carlos Osorio on 11/03/2021
 */

val networkModule = module {
    single<ICognitoRepositoryNetwork> { CognitoNetwork() }
}
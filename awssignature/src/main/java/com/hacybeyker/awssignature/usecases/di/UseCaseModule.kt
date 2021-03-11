package com.hacybeyker.awssignature.usecases.di

import com.hacybeyker.awssignature.usecases.usecase.CognitoUseCase
import org.koin.dsl.module

/**
 * Created by Carlos Osorio on 11/03/2021
 */

val useCaseModule = module {
    single { CognitoUseCase() }
}
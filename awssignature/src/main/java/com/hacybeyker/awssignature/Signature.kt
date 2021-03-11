package com.hacybeyker.awssignature

import android.app.Application
import com.hacybeyker.awssignature.entities.Signer
import com.hacybeyker.awssignature.repository.di.networkModule
import com.hacybeyker.awssignature.repository.di.retrofitModule
import com.hacybeyker.awssignature.repository.utils.AWS_REGION
import com.hacybeyker.awssignature.repository.utils.AWS_SERVICE
import com.hacybeyker.awssignature.usecases.di.useCaseModule
import com.hacybeyker.awssignature.usecases.usecase.CognitoUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.KoinComponent
import org.koin.dsl.koinApplication

/**
 * Created by Carlos Osorio on 11/03/2021
 */

object Signature {
    lateinit var koinApplication: KoinApplication
    private val useCase: CognitoUseCase = CognitoUseCase()

    @Synchronized
    fun setApplication(app: Application) {
        koinApplication = koinApplication {
            androidContext(app.applicationContext)
            modules(
                listOf(
                    retrofitModule,
                    networkModule,
                    useCaseModule
                )
            )
        }
    }

    suspend fun fetchSignature(
        region: String = AWS_REGION,
        service: String = AWS_SERVICE,
        identityPoolId: String,
        url: String,
        host: String,
        body: String,
        result: suspend (Signer?) -> Unit
    ) {
        useCase.getSignatureAws(
            region = region,
            service = service,
            identityPoolId = identityPoolId,
            url = url,
            host = host,
            body = body
        ) {
            it?.let { result.invoke(it) }
        }
    }
}

internal interface SignatureComponent : KoinComponent {
    override fun getKoin(): Koin {
        return Signature.koinApplication.koin
    }
}
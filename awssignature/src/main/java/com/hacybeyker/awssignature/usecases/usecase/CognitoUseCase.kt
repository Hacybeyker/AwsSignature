package com.hacybeyker.awssignature.usecases.usecase

import com.hacybeyker.awssignature.SignatureComponent
import com.hacybeyker.awssignature.entities.Credentials
import com.hacybeyker.awssignature.entities.Signer
import com.hacybeyker.awssignature.repository.utils.AWS_REGION
import com.hacybeyker.awssignature.repository.utils.AWS_SERVICE
import com.hacybeyker.awssignature.usecases.repository.ICognitoRepositoryNetwork
import org.koin.core.inject

/**
 * Created by Carlos Osorio on 11/03/2021
 */

class CognitoUseCase : SignatureComponent {

    private val iCognitoRepositoryNetwork: ICognitoRepositoryNetwork by inject()

    private suspend fun generateIdentityID(identityPoolId: String): String {
        return iCognitoRepositoryNetwork.generateIdentityID(identityPoolId = identityPoolId)
    }

    private suspend fun generateCredentials(identityId: String): Credentials {
        return iCognitoRepositoryNetwork.generateCredentials(identityId = identityId)
    }

    suspend fun getSignatureAws(
        region: String = AWS_REGION,
        service: String = AWS_SERVICE,
        identityPoolId: String,
        url: String,
        host: String,
        body: String,
        result: suspend (Signer?) -> Unit
    ) {
        val identityId = generateIdentityID(identityPoolId = identityPoolId)
        val credentials = generateCredentials(identityId = identityId)
        iCognitoRepositoryNetwork.generateSigner(
            region = region,
            service = service,
            url = url,
            host = host,
            body = body,
            accessKeyId = credentials.accessKeyId,
            accessKey = credentials.secretKey,
            token = credentials.sessionToken
        ) {
            it?.let { result.invoke(it) }
        }
    }
}
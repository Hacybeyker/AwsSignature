package com.hacybeyker.awssignature.usecases.repository

import com.hacybeyker.awssignature.entities.Credentials
import com.hacybeyker.awssignature.entities.Signer

/**
 * Created by Carlos Osorio on 11/03/2021
 */

interface ICognitoRepositoryNetwork {
    suspend fun generateIdentityID(identityPoolId: String): String
    suspend fun generateCredentials(identityId: String): Credentials
    suspend fun generateSigner(
        region: String,
        service: String,
        url: String,
        host: String,
        body: String,
        accessKeyId: String,
        accessKey: String,
        token: String,
        result: suspend (Signer?) -> Unit
    )
}
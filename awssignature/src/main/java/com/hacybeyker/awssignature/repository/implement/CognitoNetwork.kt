package com.hacybeyker.awssignature.repository.implement

import com.babbel.mobile.android.commons.okhttpawssigner.OkHttpAwsV4Signer
import com.hacybeyker.awssignature.SignatureComponent
import com.hacybeyker.awssignature.entities.Credentials
import com.hacybeyker.awssignature.entities.Signer
import com.hacybeyker.awssignature.repository.model.request.IdentityRequest
import com.hacybeyker.awssignature.repository.services.ICognitoServices
import com.hacybeyker.awssignature.repository.utils.AMZ_DATE
import com.hacybeyker.awssignature.repository.utils.AUTHORIZATION
import com.hacybeyker.awssignature.repository.utils.HOST
import com.hacybeyker.awssignature.repository.utils.generateDate
import com.hacybeyker.awssignature.usecases.repository.ICognitoRepositoryNetwork
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.core.inject

/**
 * Created by Carlos Osorio on 11/03/2021
 */

class CognitoNetwork : ICognitoRepositoryNetwork, SignatureComponent {

    private val service: ICognitoServices by inject()

    override suspend fun generateIdentityID(identityPoolId: String): String {
        val response = service.generateIdentityID(IdentityRequest(identityPoolId = identityPoolId))
        var data: String? = null
        if (response.isSuccessful) {
            response.body()?.apply {
                data = this.identityId ?: ""
            }
        }
        return data ?: throw Exception("Error generating IdentityId")
    }

    override suspend fun generateCredentials(identityId: String): Credentials {
        val response = service.generateCredentials(IdentityRequest(identityId = identityId))
        var credentials: Credentials? = null
        if (response.isSuccessful) {
            response.body()?.apply {
                this.credentials?.let {
                    credentials = it.toCredentials()
                }
            }
        }
        return credentials ?: throw Exception("Error generating Credentials")
    }

    override suspend fun generateSigner(
        region: String,
        service: String,
        url: String,
        host: String,
        body: String,
        accessKeyId: String,
        accessKey: String,
        token: String,
        result: suspend (Signer?) -> Unit
    ) {
        try {
            val requestBody = body.toRequestBody("application/json".toMediaTypeOrNull())
            val httpRequest: Request = Request.Builder()
                .url(url)
                .addHeader(AMZ_DATE, generateDate())
                .addHeader(HOST, host)
                .post(requestBody)
                .build()
            val signer = OkHttpAwsV4Signer(region = region, service = service)
            val signedRequest = signer.sign(
                httpRequest,
                accessKeyId = accessKeyId,
                accessKey = accessKey
            )
            val signature = Signer(
                host = signedRequest.headers[HOST] ?: "",
                date = signedRequest.headers[AMZ_DATE] ?: "",
                authorization = signedRequest.headers[AUTHORIZATION] ?: "",
                token = token
            )
            result.invoke(signature)
        } catch (ex: Exception) {
            throw Exception("Error generate Signature")
        }
    }
}
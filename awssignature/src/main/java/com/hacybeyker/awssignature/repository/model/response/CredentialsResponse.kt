package com.hacybeyker.awssignature.repository.model.response

import com.google.gson.annotations.SerializedName
import com.hacybeyker.awssignature.entities.Credentials

/**
 * Created by Carlos Osorio on 11/03/2021
 */

data class CredentialsResponse(
    @SerializedName("AccessKeyId") var accessKeyId: String?,
    @SerializedName("Expiration") var expiration: Float?,
    @SerializedName("SecretKey") var secretKey: String?,
    @SerializedName("SessionToken") var sessionToken: String?
) {
    fun toCredentials(): Credentials {
        return Credentials(
            accessKeyId = accessKeyId ?: "",
            expiration = expiration ?: 0.0F,
            secretKey = secretKey ?: "",
            sessionToken = sessionToken ?: ""
        )
    }
}
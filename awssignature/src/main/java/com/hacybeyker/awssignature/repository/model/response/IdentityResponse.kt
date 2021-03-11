package com.hacybeyker.awssignature.repository.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Carlos Osorio on 11/03/2021
 */

data class IdentityResponse(
    @SerializedName("Credentials") var credentials: CredentialsResponse? = null,
    @SerializedName("IdentityId") var identityId: String? = null
)
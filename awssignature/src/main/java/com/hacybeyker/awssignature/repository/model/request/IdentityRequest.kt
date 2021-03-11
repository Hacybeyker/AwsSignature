package com.hacybeyker.awssignature.repository.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Carlos Osorio on 11/03/2021
 */

data class IdentityRequest(
    @SerializedName("IdentityPoolId") var identityPoolId: String? = null,
    @SerializedName("IdentityId") var identityId: String? = null
)
package com.hacybeyker.awssignature.repository.services

import com.hacybeyker.awssignature.repository.model.request.IdentityRequest
import com.hacybeyker.awssignature.repository.model.response.IdentityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Carlos Osorio on 11/03/2021
 */

interface ICognitoServices {
    @POST("/")
    @Headers(
        "content-type:application/x-amz-json-1.1",
        "x-amz-target:AWSCognitoIdentityService.GetId"
    )
    suspend fun generateIdentityID(@Body identityRequest: IdentityRequest): Response<IdentityResponse>

    @POST("/")
    @Headers(
        "content-type:application/x-amz-json-1.1",
        "x-amz-target:AWSCognitoIdentityService.GetCredentialsForIdentity"
    )
    suspend fun generateCredentials(@Body identityRequest: IdentityRequest): Response<IdentityResponse>
}
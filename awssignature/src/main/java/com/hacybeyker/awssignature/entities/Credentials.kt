package com.hacybeyker.awssignature.entities

/**
 * Created by Carlos Osorio on 11/03/2021
 */

data class Credentials(
    val accessKeyId: String,
    val expiration: Float,
    val secretKey: String,
    val sessionToken: String
)
package com.hacybeyker.awssignature.entities

/**
 * Created by Carlos Osorio on 11/03/2021
 */

data class Signer(
    val host: String,
    val date: String,
    val authorization: String,
    val token: String
)
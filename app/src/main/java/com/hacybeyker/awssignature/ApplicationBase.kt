package com.hacybeyker.awssignature

import android.app.Application

/**
 * Created by Carlos Osorio on 11/03/2021
 */

class ApplicationBase : Application() {
    override fun onCreate() {
        super.onCreate()
        Signature.setApplication(this, "https://cognito-identity.us-east-2.amazonaws.com")
    }
}
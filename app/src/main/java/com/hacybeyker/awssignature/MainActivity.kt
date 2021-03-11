package com.hacybeyker.awssignature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
            Signature.fetchSignature(
                identityPoolId = "your_identity_pool_id",
                url = "your_path_complete",
                host = "your_path_only_host",
                body = "your_request_body_in_string"
            ) {
                it?.let {
                    Log.d(TAG, "Here - onCreate authorization: ${it.authorization}")
                    Log.d(TAG, "Here - onCreate date: ${it.date}")
                    Log.d(TAG, "Here - onCreate token: ${it.token}")
                }
            }
        }
    }
}
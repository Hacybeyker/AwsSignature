# Aws Signature
Is a library that allows you to sign a frame, using the identitypoolid.

### Gradle 
Add jitpack in project's `build.gradle`
```Gradle
    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

Add the library in the app `build.gradle`
```gradle
dependencies {
    implementation 'com.github.hacybeyker:awssignature:1.0.0'
}
```

### Usage
Initialize the library
```kotlin
class ApplicationBase : Application() {
    override fun onCreate() {
        super.onCreate()
        ...
        Signature.setApplication(this, "YOUR_BASE_URL_AWS")
    }
}
```

Method to generate and obtain the signature
```kotlin
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
```

#### Note
The library must be run under a coroutina.
Returns a Signer object which contains: host, date, authorization and token.
Parameters used to send them as a header in a request.

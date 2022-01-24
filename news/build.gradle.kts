plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    thirparty {
        implementation(glide)
    }
    implementation(JakeWharton.timber)

    fastadapter {
        implementation(fastAdapter)
        implementation(fastAdapterBinding)
        implementation(fastAdapterDiff)
        implementation(fastAdapterPaged)
    }

    implementation(AndroidX.fragment)
    implementation(AndroidX.navigation.uiKtx)
    implementation(AndroidX.navigation.fragmentKtx)

    android {
        implementation(hiltViewModel)
    }

    implementation(Google.dagger.hilt.android)
    implementation(Google.android.material)
    kapt(Google.dagger.hilt.compiler)

    implementation(Modules.common)
    implementation(Modules.domain)
    implementation(Modules.core)
}


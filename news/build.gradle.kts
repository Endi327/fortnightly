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
        implementation(timber)
        implementation(glide)
    }

    fastadapter {
        implementation(fastAdapter)
        implementation(fastAdapterBinding)
        implementation(fastAdapterDiff)
        implementation(fastAdapterPaged)
    }

    android {
        implementation(fragment)
        implementation(navigationUI)
        implementation(navigationFragment)
        implementation(hiltViewModel)
    }

    google {
        implementation(hilt)
        implementation(design)
        kapt(hiltCompiler)
    }

    implementation(Modules.common)
    implementation(Modules.domain)
    implementation(Modules.core)
}

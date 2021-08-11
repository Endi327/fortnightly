plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    fastadapter {
        implementation(fastAdapter)
        implementation(fastAdapterBinding)
        implementation(fastAdapterUtils)
        implementation(fastAdapterDiff)
    }

    thirparty {
        implementation(timber)
    }

    android {
        implementation(core)
    }
}

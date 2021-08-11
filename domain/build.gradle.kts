plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    thirparty {
        implementation(timber)

        implementation(retrofit)
    }

    google {
        implementation(hilt)
        kapt(hiltCompiler)
    }

    kotlinx {
        implementation(coroutinesCore)
    }
}

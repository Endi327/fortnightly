plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    android {
        implementation(appCompat)
        implementation(core)
        implementation(fragment)
        implementation(lifecycleProcess)
        implementation(lifecycleExtension)
        implementation(lifecycleRuntime)
        implementation(lifecycleCommonJava)
    }

    thirparty {
        implementation(timber)
    }

    kotlinx {
        implementation(coroutinesCore)
    }

    implementation(Modules.domain)
    implementation(Modules.common)
    implementation(Modules.resources)
}

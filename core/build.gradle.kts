plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.fragment.ktx)
    implementation(AndroidX.fragment.ktx)
    implementation(AndroidX.lifecycle.process)
    implementation(AndroidX.lifecycle.extensions)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.lifecycle.commonJava8)

    implementation(JakeWharton.timber)

    implementation(KotlinX.coroutines.core)

    implementation(Modules.domain)
    implementation(Modules.common)
    implementation(Modules.resources)
}
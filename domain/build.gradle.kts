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
    implementation(JakeWharton.timber)
    implementation(Square.retrofit2)

    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation(KotlinX.coroutines.core)
}
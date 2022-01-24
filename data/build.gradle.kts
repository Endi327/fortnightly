plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(JakeWharton.timber)
    implementation(Square.retrofit2)
    implementation(Square.okHttp3.loggingInterceptor)

    implementation(AndroidX.core.ktx)

    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.android)

    google {
        implementation(gson)
        implementation(gsonConverter)
    }

    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation(Modules.domain)
    implementation(Modules.core)
    implementation(Modules.common)
}

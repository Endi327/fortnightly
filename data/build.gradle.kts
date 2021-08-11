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
    thirparty {
        implementation(timber)

        implementation(okhttpLogging)
        implementation(retrofit)
    }

    android {
        implementation(core)
    }

    kotlinx {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
    }

    google {
        implementation(hilt)
        implementation(gson)
        implementation(gsonConverter)
        kapt(hiltCompiler)
    }

    implementation(Modules.domain)
    implementation(Modules.core)
    implementation(Modules.common)
}

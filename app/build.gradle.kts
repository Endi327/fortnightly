plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.align.fortnightly"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BaseUrl", "\"https://newsapi.org/\"")
            buildConfigField("String", "ApiKey", "\"f71af7261c434b5d8be60816ed910d8b\"")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BaseUrl", "\"https://newsapi.org/\"")
            buildConfigField("String", "ApiKey", "\"f71af7261c434b5d8be60816ed910d8b\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    android {
        implementation(appCompat)
        implementation(navigationUI)
        implementation(navigationFragment)
        implementation(fragment)
        implementation(paging)
        implementation(lifecycleProcess)
    }

    thirparty {
        debugImplementation(leakcanary)

        implementation(timber)

        implementation(retrofit)
        implementation(okhttpLogging)
    }

    google {
        implementation(design)
        implementation(gson)
        implementation(hilt)
        kapt(hiltCompiler)
    }

    kotlinx {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
    }

    implementation(Modules.core)
    implementation(Modules.common)
    implementation(Modules.domain)
    implementation(Modules.data)
    implementation(Modules.resources)
    implementation(Modules.news)
}
buildscript {
    repositories {
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
        mavenCentral()
    }
}

repositories {
    google()
    maven { url = uri("https://plugins.gradle.org/m2/") }
    maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("src/main/kotlin")
        }
    }
}

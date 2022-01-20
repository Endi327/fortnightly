buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("src/main/kotlin")
        }
    }
}

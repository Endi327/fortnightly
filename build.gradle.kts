// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.5.10")
    repositories {
        google()
        mavenCentral()
        gradleM2()
        kotlinx()
        jitpack()
    }
    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${CommonVersions.kotlin}")

        android {
            classpath(navigationSafeArgs)
        }

        google {
            classpath(hiltPlugin)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        gradleM2()
        kotlinx()
        jitpack()
    }
}

subprojects {
    afterEvaluate {
        if (isAndroidLibrary || isAndroidApplication) {
            extensions.getByType<com.android.build.gradle.BaseExtension>().apply {
                compileSdkVersion(CommonVersions.targetsdk)
                buildToolsVersion = CommonVersions.buildTools

                defaultConfig {
                    minSdkVersion(CommonVersions.minsdk)
                    targetSdkVersion(CommonVersions.targetsdk)

                    vectorDrawables {
                        setGeneratedDensities(emptyList())
                    }
                }

                buildFeatures.viewBinding = true

                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                }

                dependencies {
                    "coreLibraryDesugaring"("com.android.tools:desugar_jdk_libs:1.1.1")
                }

                sourceSets {
                    val main by getting {
                        java.srcDirs(projectDir.resolve("src/main/kotlin"))
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }

            if (isAndroidLibrary) {
                extensions.getByType<com.android.build.gradle.BaseExtension>().apply {
                    defaultConfig {
                        versionCode = 1
                        versionName = "1.0"

                        if (project.file("proguard-rules.pro").exists()) {
                            consumerProguardFiles("proguard-rules.pro")
                        }
                    }
                }
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
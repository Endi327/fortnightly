import org.gradle.api.Project

val Project.isAndroidApplication: Boolean
    get() = plugins.hasPlugin("com.android.application")


val Project.isAndroidLibrary: Boolean
    get() = plugins.hasPlugin("com.android.library")
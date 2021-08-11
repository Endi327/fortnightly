import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.gradleM2() = maven { setUrl("https://plugins.gradle.org/m2/") }

fun RepositoryHandler.jitpack() = maven { setUrl("https://jitpack.io") }

fun RepositoryHandler.mavenGoogle() = maven { setUrl("https://maven.google.com") }

fun RepositoryHandler.kotlinx() = maven { setUrl("https://kotlin.bintray.com/kotlinx") }

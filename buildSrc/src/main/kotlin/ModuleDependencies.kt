import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

interface Modules {
    val core: Dependency
    val common: Dependency
    val data: Dependency
    val domain: Dependency
    val news: Dependency
    val resources: Dependency
}

val DependencyHandler.Modules: Modules
    get() = object : Modules {
        override val core: Dependency
            get() = project(":core")

        override val common: Dependency
            get() = project(":common")

        override val data: Dependency
            get() = project(":data")

        override val domain: Dependency
            get() = project(":domain")

        override val news: Dependency
            get() = project(":news")

        override val resources: Dependency
            get() = project(":resources")
    }

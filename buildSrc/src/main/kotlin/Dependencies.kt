import dependencies.Android
import dependencies.AndroidDependencies
import dependencies.FastAdapter
import dependencies.FastAdapterDependencies
import dependencies.Google
import dependencies.GoogleDependencies
import dependencies.KotlinX
import dependencies.KotlinXDependencies
import dependencies.Testing
import dependencies.TestingDependencies
import dependencies.ThirdParty
import dependencies.ThirdPartyDependencies
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.android(block: AndroidDependencies.() -> Unit) = Android.block()

fun DependencyHandlerScope.kotlinx(block: KotlinXDependencies.() -> Unit) = KotlinX.block()

fun DependencyHandlerScope.google(block: GoogleDependencies.() -> Unit) = Google.block()

fun DependencyHandlerScope.thirparty(block: ThirdPartyDependencies.() -> Unit) = ThirdParty.block()

fun DependencyHandlerScope.testing(block: TestingDependencies.() -> Unit) = Testing.block()

fun DependencyHandlerScope.fastadapter(block: FastAdapterDependencies.() -> Unit) = FastAdapter.block()

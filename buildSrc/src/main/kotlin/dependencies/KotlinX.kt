package dependencies

interface KotlinXDependencies {
    val coroutinesCore: String
    val coroutinesAndroid: String
    val coroutinesTest: String
    val kotlinTest: String
}

internal object KotlinX : KotlinXDependencies {
    private const val coroutines = "1.5.0"

    override val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines"
    override val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines"
    override val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines"
    override val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${CommonVersions.kotlin}"
}

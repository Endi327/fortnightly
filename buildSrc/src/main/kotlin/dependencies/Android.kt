package dependencies

interface AndroidDependencies {
    val appCompat: String
    val core: String
    val navigationUI: String
    val navigationFragment: String
    val navigationSafeArgs: String
    val fragment: String
    val room: String
    val roomCompiler: String
    val roomAnnotations: String
    val roomKtx: String
    val hiltViewModel: String
    val paging: String
    val parcelize: String
    val lifecycleProcess: String
    val lifecycleExtension: String
    val lifecycleCommonJava: String
    val lifecycleRuntime: String
}

internal object Android : AndroidDependencies {
    private const val roomVersion = "2.3.0"
    private const val hiltersion = "1.0.0-alpha03"

    override val appCompat = "androidx.appcompat:appcompat:1.3.0"
    override val core = "androidx.core:core-ktx:1.3.1"
    override val navigationUI = "androidx.navigation:navigation-ui-ktx:2.3.5"
    override val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.3.5"
    override val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0"
    override val fragment: String = "androidx.fragment:fragment-ktx:1.3.4"
    override val room = "androidx.room:room-runtime:$roomVersion"
    override val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    override val roomAnnotations: String = "androidx.room:room-common:$roomVersion"
    override val roomKtx = "androidx.room:room-ktx:$roomVersion"
    override val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltersion"
    override val paging = "androidx.paging:paging-runtime:2.1.2"
    override val parcelize = "org.jetbrains.kotlin:kotlin-android-extensions:${CommonVersions.kotlin}"
    override val lifecycleProcess = "androidx.lifecycle:lifecycle-process:2.3.1"
    override val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    override val lifecycleCommonJava = "androidx.lifecycle:lifecycle-common-java8:2.3.1"
    override val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
}

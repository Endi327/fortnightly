package dependencies

interface TestingDependencies {
    val junit: String
    val mockk: String
    val mockkAndroid: String
    val room: String
    val junitExt: String
    val core: String
    val runner: String
    val rules: String
    val truth: String
    val orchestrator: String
    val espressoCore: String
    val fragment: String
    val navigationTesting: String
    val espressoContrib: String
    val uiautomator: String
}

internal object Testing : TestingDependencies {
    override val junit = "junit:junit:4.13.2"
    override val mockk = "io.mockk:mockk:1.11.0"
    override val mockkAndroid = "io.mockk:mockk-android:1.11.0"
    override val room = "androidx.room:room-testing:2.2.5"
    override val junitExt = "androidx.test.ext:junit:1.1.2"
    override val core = "androidx.test:core:1.2.0"
    override val runner = "androidx.test:runner:1.3.0"
    override val rules = "androidx.test:rules:1.3.0"
    override val truth = "androidx.test.ext:truth:1.2.0"
    override val orchestrator = "androidx.test:orchestrator:1.2.0"
    override val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    override val fragment = "androidx.fragment:fragment-testing:1.3.4"
    override val navigationTesting = "androidx.navigation:navigation-testing:2.3.5"
    override val espressoContrib = "androidx.test.espresso:espresso-contrib:3.3.0"
    override val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
}

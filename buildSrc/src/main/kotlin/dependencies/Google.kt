package dependencies

interface GoogleDependencies {
    val design: String
    val googleServices: String
    val hiltPlugin: String
    val hiltCompiler: String
    val hilt: String
    val gson: String
    val gsonConverter: String
}

internal object Google : GoogleDependencies {
    private const val hiltVersion = "2.36"

    override val design = "com.google.android.material:material:1.3.0"
    override val googleServices = "com.google.gms:google-services:4.3.8"
    override val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    override val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"
    override val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    override val gson: String = "com.google.code.gson:gson:2.8.7"
    override val gsonConverter: String = "com.squareup.retrofit2:converter-gson:2.6.2"
}

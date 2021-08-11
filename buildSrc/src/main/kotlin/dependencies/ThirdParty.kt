package dependencies

interface ThirdPartyDependencies {
    val timber: String
    val leakcanary: String
    val okhttpLogging: String
    val retrofit: String
    val glide: String
    val glideOkHttp: String
    val glideProcessor: String
}

internal object ThirdParty : ThirdPartyDependencies {
    private const val moshiVersion = "1.12.0"

    override val timber = "com.jakewharton.timber:timber:4.7.1"
    override val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.7"
    override val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.1"
    override val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    override val glide = "com.github.bumptech.glide:glide:4.12.0"
    override val glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:4.12.0"
    override val glideProcessor = "com.github.bumptech.glide:compiler:4.12.0"
}

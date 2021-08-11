import com.android.build.gradle.BaseExtension

fun BaseExtension.enableViewBinding() {
    buildFeatures.viewBinding = true
}

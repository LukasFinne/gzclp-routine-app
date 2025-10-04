plugins {
    id("com.android.library") version "8.13.0"
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android{
    namespace = "se.finne.lukas.gzclp"
    compileSdk = 36
}

kotlin {
    jvmToolchain(21)
}

dependencies{
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.navigation.compose)
    implementation(libs.androidx.compose.material.material.icons.extended)


    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

}
plugins {
    id("com.android.library") version "8.13.0"
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}


android{
    namespace = "se.finne.lukas.workoutrepository.wiring"
    compileSdk = 36
    defaultConfig{
        minSdk = 36
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies{
    implementation(project(":libs:workoutrepository:declaration"))
    implementation(project(":libs:workoutrepository:implementation"))

    implementation("androidx.compose.runtime:runtime:1.9.3") // Use a specific, compatible version
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
}


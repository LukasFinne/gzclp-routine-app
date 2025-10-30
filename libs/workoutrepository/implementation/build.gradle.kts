plugins {
    id("com.android.library") version "8.13.0"
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android{
    namespace = "se.finne.lukas.workoutrepository.implementation"
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
    implementation("androidx.compose.runtime:runtime:1.9.3") // Use a specific, compatible version
    testImplementation(libs.androidx.junit)
    testImplementation(kotlin("test"))
}

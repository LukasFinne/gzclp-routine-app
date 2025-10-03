plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    kotlin("plugin.serialization") version "2.0.21"
}

kotlin {
    jvmToolchain(21)
}

dependencies{
    implementation(libs.jetbrains.kotlinx.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.navigation.compose)
    testImplementation(libs.androidx.navigation.navigation.testing)
}
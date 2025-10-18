plugins {
    id("com.android.library") version "8.13.0"
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}


android{
    namespace = "se.finne.lukas.room"
    compileSdk = 36
    buildFeatures{
        compose = true
    }
}

kotlin {
    jvmToolchain(21)
}



dependencies{


    implementation(libs.androidx.startup.runtime)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation("androidx.compose.runtime:runtime:1.9.3") // Use a specific, compatible version
//    //Room
    implementation(libs.androidx.room.room.runtime)
    ksp(libs.androidx.room.room.compiler)
    implementation(libs.androidx.room.room.ktx)

}
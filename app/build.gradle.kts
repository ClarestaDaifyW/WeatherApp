plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.parcelize") // ⬅ WAJIB ditambahkan
}

android {
    namespace = "com.example.weatherapprealtime"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.weatherapprealtime"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            "String",
            "OWM_API_KEY",
            "\"${project.findProperty("OWM_API_KEY")}\""
        )
        buildFeatures {
            compose = true
            buildConfig = true   // ← WAJIB
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // Retrofit + converter
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
// atau gson
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
// Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
// Jetpack Compose (versi sesuai projectmu)
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.0")
// Play Services Location (FusedLocationProvider)
    implementation("com.google.android.gms:play-services-location:21.0.1")
// WorkManager (opsional, untuk polling/realtime)
    implementation("androidx.work:work-runtime-ktx:2.8.1")
// Optional: accompanist-permissions (jika mau lebih mudah handle permission di Compose)
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.compose.ui:ui-graphics:1.7.5")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.compose.material3:material3:1.2.0")


}
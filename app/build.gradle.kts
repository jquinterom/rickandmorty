plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)

  // KSP
  id ("com.google.devtools.ksp")

  // Hilt
  id ("com.google.dagger.hilt.android")
}

android {
  namespace = "co.mrcomondev.pro.rickandmorty"
  compileSdk = 35

  defaultConfig {
    applicationId = "co.mrcomondev.pro.rickandmorty"
    minSdk = 28
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
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
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)

  // Hilt
  implementation (libs.hilt.android)
  ksp (libs.hilt.compiler)

  // Viewmodel
  implementation(libs.androidx.lifecycle.viewmodel.ktx)

  // Coroutines
  implementation(libs.kotlinx.coroutines.android)

  // Glide
  implementation(libs.glide)

  // Retrofit
  implementation(libs.retrofit)

  // Moshi
  implementation(libs.moshi.kotlin) // Versión más reciente
  implementation(libs.converter.moshi) // Converter para Retrofit
  ksp(libs.moshi.kotlin.codegen)

  implementation(libs.moshi.adapters)
}
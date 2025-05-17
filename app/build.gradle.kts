plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)

  // KSP
  id("com.google.devtools.ksp")

  // Hilt
  id("com.google.dagger.hilt.android")

  // Serialization - Navigation
  alias (libs.plugins.kotlinx.serialization)
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
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)

  // Viewmodel
  implementation(libs.androidx.lifecycle.viewmodel.ktx)

  // Coroutines
  implementation(libs.kotlinx.coroutines.android)


  // Coil
  implementation(libs.coil.compose)

  // Retrofit
  implementation(libs.retrofit)

  // Moshi
  implementation(libs.moshi.kotlin)
  implementation(libs.converter.moshi)
  ksp(libs.moshi.kotlin.codegen)
  implementation(libs.moshi.adapters)

  // LiveData
  implementation(libs.androidx.runtime.livedata)

  // Pagination 3
  implementation (libs.androidx.paging.runtime.ktx)
  implementation (libs.androidx.paging.compose)

  // Navigation
  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)

  // Constrains
  implementation(libs.androidx.constraintlayout.compose)

  // Datastore
  implementation("androidx.datastore:datastore-preferences:1.1.6")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")
}
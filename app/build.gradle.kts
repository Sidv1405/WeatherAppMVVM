plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") //Su dung anotation Room, Dagger,...
    id("kotlin-parcelize") //
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //OkHttp
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Gson
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.8.9")

    //Lifecycle - Quan ly Activity va Fragment
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-extensions
    runtimeOnly("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-runtime-ktx
    runtimeOnly("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx
    runtimeOnly("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-livedata-ktx
    runtimeOnly("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    //ViewModel
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("com.github.Dimezis:BlurView:version-2.0.5") //Lam mo
    implementation("com.github.bumptech.glide:glide:4.16.0") //Xu ly hinh anh

    //Other
//    implementation ("com.github.MatteoBattilana:WeatherView:3.0.0") //Giao dien thoi tiet
}

kapt {
    correctErrorTypes = true
}
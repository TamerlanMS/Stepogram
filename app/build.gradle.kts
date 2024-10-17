plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.stepogram"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stepogram"
        minSdk = 29
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase dependencies
    implementation(platform(libs.firebase.bom.v3330))
    implementation(libs.google.firebase.auth)
    implementation(libs.firebase.database)

    // Retrofit for HTTP requests
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    // Android architecture components
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // RecyclerView, Material Design
    implementation(libs.recyclerview)
    implementation(libs.material.v161)

    // Glide for image loading
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Other dependencies
    implementation(libs.constraintlayout)
}


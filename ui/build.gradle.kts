plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        buildToolsVersion = "30.0.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc02"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    namespace = "com.dosei.music.scoreconverter.ui"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.0")
    implementation("com.getkeepsafe.taptargetview:taptargetview:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    implementation(libs.core.ktx)
    implementation(libs.app.compat)
    implementation(libs.android.material)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycle.runtime)
    testImplementation(libs.junit.core)
    androidTestImplementation(libs.android.test.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.compose.tooling.ui)
}

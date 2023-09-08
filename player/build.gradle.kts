plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        buildToolsVersion = "30.0.2"
        targetSdk = 32
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "com.dosei.music.scoreconverter.player"
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.core.ktx)
    implementation(libs.app.compat)
    implementation(libs.midi.driver)
    testImplementation(libs.junit.core)
    testImplementation(libs.mockk)
}
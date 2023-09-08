plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = libs.versions.compiler.kotlin.ext.get()
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    namespace = "com.dosei.music.scoreconverter.ui"
}

kotlin {
    jvmToolchain(8)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.constraint.layout)

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

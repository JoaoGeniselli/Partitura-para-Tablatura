plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = Versions.Sdk.compile
    defaultConfig {
        applicationId = "com.dosei.music.scoreconverter"
        minSdk = Versions.Sdk.minimum
        targetSdk = Versions.Sdk.target
        versionCode = Versions.App.code
        versionName = Versions.App.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val secrets = SecretsAPI.forProject(rootProject)

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "admob_application_id", secrets.getString(SecretsKeys.adMobAppId))
            resValue("string", "admob_home_banner_id", BuildConstants.adMobFakeBannerId)
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "admob_application_id", secrets.getString(SecretsKeys.adMobAppId))
            resValue(
                "string",
                "admob_home_banner_id",
                secrets.getString(SecretsKeys.adMobHomeBannerId)
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc01"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(path = ":ui"))
    implementation(project(path = ":player"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.app.compat)
    implementation(libs.core.ktx)
    implementation(libs.constraint.layout)
    implementation(libs.recycler.view)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics.core)
    implementation(libs.koin.android)
    implementation(libs.koin.scope)
    implementation(libs.koin.view.model)
    implementation(libs.play.services.ads)
    implementation(libs.android.material)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycle.runtime)

    testImplementation(libs.junit.core)

    androidTestImplementation(libs.android.test.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.junit)

    debugImplementation(libs.compose.tooling.ui)
}

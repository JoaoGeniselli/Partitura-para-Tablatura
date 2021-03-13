plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(Versions.Sdk.compile)
    defaultConfig {
        applicationId = "com.dosei.music.scoreconverter"
        minSdkVersion(Versions.Sdk.minimum)
        targetSdkVersion(Versions.Sdk.target)
        versionCode = Versions.App.code
        versionName = Versions.App.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val secrets = SecretsAPI.forProject(rootProject)

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            resValue("string", "admob_application_id", secrets.getString(SecretsKeys.adMobAppId))
            resValue("string", "admob_home_banner_id", secrets.getString(SecretsKeys.fakeAdMobHomeBannerId))
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            resValue("string", "admob_application_id", secrets.getString(SecretsKeys.adMobAppId))
            resValue("string", "admob_home_banner_id", secrets.getString(SecretsKeys.adMobHomeBannerId))
        }
    }
}

dependencies {
    implementation(project(path = ":ui"))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.recyclerView)
    implementation(Dependencies.Firebase.analytics)
    implementation(Dependencies.Firebase.crashlytics)
    implementation(Dependencies.koin)
    implementation(Dependencies.koinScope)
    implementation(Dependencies.koinViewModel)
    implementation(Dependencies.playServicesAds)

    testImplementation(Dependencies.junit)

    androidTestImplementation(Dependencies.AndroidX.junitExtensions)
    androidTestImplementation(Dependencies.AndroidX.espresso)
}

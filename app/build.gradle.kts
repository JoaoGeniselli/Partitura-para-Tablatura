import java.util.*
import java.io.FileInputStream

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

    val secretPropertiesFile = rootProject.file("secret.properties")
    val secretProperties = Properties()
    secretProperties.load(FileInputStream(secretPropertiesFile))

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            resValue("string", "admob_application_id", secretProperties["ADMOB_APPLICATION_ID"] as String)
            resValue("string", "admob_home_banner_id", secretProperties["FAKE_ADMOB_HOME_BANNER_ID"] as String)
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            resValue("string", "admob_application_id", secretProperties["ADMOB_APPLICATION_ID"] as String)
            resValue("string", "admob_home_banner_id", secretProperties["ADMOB_HOME_BANNER_ID"] as String)
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

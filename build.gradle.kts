// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.google.gms:google-services:4.3.3")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.pkg.github.com/JoaoGeniselli/ktransposer") {
            val secrets = SecretsAPI.forProject(rootProject)
            credentials {
                username = secrets.getString("GITHUB_PACKAGES_USER")
                password = secrets.getString("GITHUB_PACKAGES_TOKEN")
            }
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}

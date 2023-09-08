// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.6.21")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.5.2")
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

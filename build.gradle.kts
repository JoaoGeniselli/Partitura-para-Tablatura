// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradle.plugin)
        classpath(libs.kotlin.gradle)
        classpath(libs.google.play.services)
        classpath(libs.firebase.crashlytics.gradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.pkg.github.com/JoaoGeniselli/ktransposer") {
            val secrets = SecretsAPI.forProject(rootProject)
            print("Received User: ")
            println(secrets.getString("GITHUB_PACKAGES_USER"))
            credentials {
                username = secrets.getString("GITHUB_PACKAGES_USER")
                password = secrets.getString("GITHUB_PACKAGES_TOKEN")
            }
        }
        maven(url = "https://maven.pkg.github.com/JoaoGeniselli/arpeggio") {
            val secrets = SecretsAPI.forProject(rootProject)
            print("Received User: ")
            println(secrets.getString("GITHUB_PACKAGES_USER"))
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

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(Config.Android.androidGradle)
        classpath(Config.Kotlin.kotlinGradle)
        classpath(Config.Android.Hilt.hiltAndroidGradle)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


object Config {
    const val applicationId = "base.architecture"

    object Android {
        const val core = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.2"
        const val material = "com.google.android.material:material:1.6.1"

        const val androidGradle = "com.android.tools.build:gradle:7.0.2"

        object KTX {
            const val activity = "androidx.activity:activity-ktx:1.1.0"
            const val fragment = "androidx.fragment:fragment-ktx:1.2.5"
        }

        object Room {
            private const val roomVersion = "2.4.2"
            const val runtime = "androidx.room:room-runtime:$roomVersion"
            const val ktx = "androidx.room:room-ktx:$roomVersion"
            const val compiler = "androidx.room:room-compiler:$roomVersion"
        }

        object Test {
            const val jUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
            const val mockk = "io.mockk:mockk:1.11.0"
            const val core = "androidx.arch.core:core-testing:2.1.0"
            const val jUnit = "androidx.test.ext:junit:1.1.3"
            const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
            const val ext = "androidx.test.ext:junit:1.1.3"
        }

        object Hilt {
            private const val hiltVersion = "2.38.1"
            const val hiltAndroidGradle =
                "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
            const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
            const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
            const val hiltLifecycleViewModel =
                "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        }

        object Splash {
            const val splashScreen = "androidx.core:core-splashscreen:1.0.0-beta01"
        }

        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    }

    object Lottie {
        private const val lottieVersion = "5.2.0"
        const val lottie = "com.airbnb.android:lottie:$lottieVersion"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:4.11.0"
        const val glideCompiler = "com.github.bumptech.glide:compiler:4.11.0"
    }

    object Square {
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitGsonConverter =
            "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val retrofitRxJava3Adapter =
            "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"
        const val retrofitRxJava2Adapter =
            "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"

        const val otto = "com.squareup:otto:1.3.8"

        private const val okhttpVersion = "4.9.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    }

    object Google {
        const val material = "com.google.android.material:material:1.4.0"
        const val playServiceAuth = "com.google.android.gms:play-services-auth-api-phone:17.5.1"
        const val playServiceLocation = "com.google.android.gms:play-services-location:18.0.0"
        const val gson = "com.google.code.gson:gson:2.8.8"
    }

    object Kotlin {
        private const val kotlinVersion = "1.6.0"

        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

        object Coroutine {
            private const val coroutineVersion = "1.5.2"
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
            const val coroutineTest =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
        }
    }

    object Plugins {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val kotlin = "org.jetbrains.kotlin.android"
        const val hilt = "dagger.hilt.android.plugin"
        const val kapt = "kotlin-kapt"
    }

    object Version {
        const val buildToolVersion = "30.0.3"
        const val minSdk = 23
        const val compileSdk = 31
        const val targetSdk = 31
        const val versionCode = 1
        const val versionName = "0.0.1"
    }

    object Modules {
        const val data = ":data"
        const val domain = ":domain"
    }
}

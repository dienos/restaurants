plugins {
    id(Config.Plugins.library)
    id(Config.Plugins.kotlin)
    id(Config.Plugins.hilt)
    id(Config.Plugins.kapt)
}

android {
    compileSdk = Config.Version.compileSdk

    defaultConfig {
        minSdk = Config.Version.minSdk
        targetSdk = Config.Version.targetSdk
        testInstrumentationRunner = Config.Android.Test.jUnitRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Config.Square.retrofit)
    implementation(Config.Square.retrofitGsonConverter)
    implementation(Config.Square.otto)
    implementation(Config.Square.retrofitGsonConverter)
    implementation(Config.Square.retrofitRxJava3Adapter)
    implementation(Config.Square.okhttp)
    implementation(Config.Square.okhttpLogging)

    implementation(Config.Android.Room.runtime)
    annotationProcessor(Config.Android.Room.compiler)

    implementation(Config.Android.Hilt.hiltAndroid)
    kapt(Config.Android.Hilt.hiltAndroidCompiler)
    kapt(Config.Android.Hilt.hiltCompiler)
    implementation(Config.Android.Hilt.hiltLifecycleViewModel)
}
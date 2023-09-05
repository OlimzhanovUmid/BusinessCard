import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.uolimzhanov.businesscard"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uolimzhanov.businesscard"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.7-1"
        resourceConfigurations.addAll(setOf("en", "ru"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            enableV1Signing = false
            enableV2Signing = true
        }
        create("release") {
            enableV1Signing = false
            enableV2Signing = true
        }
    }

    buildTypes {
        debug {
            versionNameSuffix = "-debug"
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            postprocessing {
                isRemoveUnusedCode = true
                isRemoveUnusedResources = true
                isObfuscate = true
                isOptimizeCode = true
            }
            proguardFiles(
                getDefaultProguardFile("proguard-defaults.txt"),
                "proguard-rules.pro"
            )

            applicationVariants.all {
                this.outputs.all {
                    this as BaseVariantOutputImpl
                    if (outputFileName == ("app-release.apk")) {
                        outputFileName = "BusinessCard-v${defaultConfig.versionName}.apk"
                    }
                }
            }
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

}

kapt {
    correctErrorTypes = true
}

dependencies {
    val versionCore: String by project
    val versionCompose: String by project
    val versionM3: String by project
    val versionWork: String by project
    val versionAccompanist: String by project
    val versionMedia3: String by project
    val versionDagger: String by project
    val versionHilt: String by project
    val versionKsp: String by project
    val versionRoom: String by project
    val versionCoil: String by project
    
    implementation("androidx.core:core-ktx:$versionCore")
    implementation("androidx.activity:activity-compose:1.8.0-alpha07")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-alpha01")
    implementation("androidx.compose.ui:ui:$versionCompose")
    implementation("androidx.compose.ui:ui-tooling-preview:$versionCompose")
    implementation("androidx.compose.ui:ui-util:$versionCompose")
    implementation("androidx.compose.material3:material3:$versionM3")
    implementation("androidx.compose.material3:material3-window-size-class:$versionM3")
    implementation("androidx.startup:startup-runtime:1.2.0-alpha02")
    implementation("androidx.work:work-runtime-ktx:$versionWork")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.window:window:1.2.0-beta01")
    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")
    implementation("androidx.palette:palette-ktx:1.0.0")
    implementation("com.google.accompanist:accompanist-permissions:$versionAccompanist")
    implementation("com.google.accompanist:accompanist-navigation-animation:$versionAccompanist")
    implementation("com.google.accompanist:accompanist-drawablepainter:$versionAccompanist")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$versionAccompanist")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("com.google.dagger:hilt-android:$versionDagger")
    kapt("com.google.dagger:hilt-compiler:$versionDagger")
    implementation("androidx.hilt:hilt-work:$versionHilt")
    kapt("androidx.hilt:hilt-compiler:$versionHilt")
    implementation("androidx.hilt:hilt-navigation-compose:$versionHilt")

    debugImplementation("androidx.compose.ui:ui-tooling:$versionCompose")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.2.0-beta01")
    androidTestImplementation("androidx.test:runner:1.6.0-alpha04")
    androidTestImplementation("androidx.work:work-testing:$versionWork")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$versionDagger")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$versionDagger")


    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("io.coil-kt:coil-compose:$versionCoil")
    implementation("io.coil-kt:coil-svg:$versionCoil")

    implementation("androidx.room:room-ktx:$versionRoom")
    ksp("androidx.room:room-compiler:$versionRoom")
}
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dagger)
    alias(libs.plugins.room)
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.uolimzhanov.businesscard"
    compileSdk = libs.versions.sdk.target.get().toInt()

    defaultConfig {
        applicationId = "com.uolimzhanov.businesscard"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
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
            isMinifyEnabled = true
            isShrinkResources = true

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
        buildConfig = true
    }
}

dependencies {
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)

    implementation(libs.bundles.dagger.hilt)
    ksp(libs.bundles.dagger.hilt.compiler)

    implementation(libs.room)
    ksp(libs.room.compiler)

    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.bundles.test)
}
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
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
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.workmanager)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.datastore)

    implementation(libs.bundles.compose)
    implementation(libs.bundles.dagger.hilt)
    ksp(libs.bundles.dagger.hilt.compiler)
    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.accompanist.navigation)

    implementation(libs.timber)
    implementation(libs.bundles.coil)

    debugImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.bundles.test)
    kspAndroidTest(libs.dagger.test.compiler)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.guess_the_number"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.guess_the_number"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        packagingOptions {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/NOTICE.md")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/LICENSE-notice.md")
        }
    }
}

dependencies {
    val nav_version = "2.7.5"

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation("androidx.navigation:navigation-testing:2.6.0")

    // For unit tests
    testImplementation ("io.mockk:mockk:1.13.2")

    // For Android instrumented tests
    androidTestImplementation ("io.mockk:mockk-android:1.13.2")

    // Compose UI testing dependencies
    //androidTestImplementation "androidx.compose.ui:ui-test-junit4:1.3.0"

    implementation ("androidx.navigation:navigation-compose:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.5")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.3.0")

    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
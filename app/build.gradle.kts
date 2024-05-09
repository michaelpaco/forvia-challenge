plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.wazowski.forviachallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wazowski.forviachallenge"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.wazowski.forviachallenge.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    val nav_version = "2.7.7"
    val hilt_version = "2.51.1"
    val room_version = "2.6.1"
    val moshi_version = "1.15.1"
    val retrofit_version = "2.11.0"
    val compose_bom_version = "2024.05.00"
    val hilt_compiler_version = "1.2.0"
    val moshi_converter_version = "2.9.0"
    val hilt_navigation_compose_version = "1.2.0"

    // Core
    implementation("androidx.core:core-ktx:1.13.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Compose
    implementation(platform("androidx.compose:compose-bom:$compose_bom_version"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-text-google-fonts")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Navigation
    implementation("androidx.navigation:navigation-runtime-ktx:$nav_version")
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Http
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$moshi_converter_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    //noinspection KaptUsageInsteadOfKsp
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshi_version")

    // Image loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // DI
    implementation("com.google.dagger:hilt-android:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:$hilt_navigation_compose_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    kapt("androidx.hilt:hilt-compiler:$hilt_compiler_version")

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.8.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hilt_version")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hilt_version")
    // ...with Java.
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:$hilt_version")
}
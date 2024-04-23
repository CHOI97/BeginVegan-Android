
plugins {
    id(Plugins.ANDROID_LIBRARY)
}

android {
    namespace = "com.example.data"
    compileSdk = DefaultConfig.COMPILE_SDK_VERSION
}

dependencies {
    implementation(project(":domain"))
}
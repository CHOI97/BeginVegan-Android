import org.gradle.api.artifacts.dsl.Dependencies

plugins {

    id(Plugins.ANDROID_LIBRARY)
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(project(":domain"))
}
plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    testImplementation(kotlin("test-junit"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
}

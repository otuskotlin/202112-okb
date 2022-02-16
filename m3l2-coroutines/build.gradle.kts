plugins {
    kotlin("jvm")
}

group = "ru.otus.okb"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

    testImplementation(kotlin("test-junit"))
}

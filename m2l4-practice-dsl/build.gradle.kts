plugins {
    kotlin("jvm")
}

group = "ru.otus.okb.first"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test-junit"))
}

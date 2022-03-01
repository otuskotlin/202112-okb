plugins {
    kotlin("js")
}

group = "ru.otus.okb.first"
version = "0.0.1"


repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        useCommonJs()
        binaries.executable()
        browser {
            testTask {
                useKarma {
                    useChromiumHeadless()
                }
            }
        }
        nodejs()
    }
}

dependencies {
    implementation(npm("sort-array", "1.0.0"))
    implementation(npm("decamelize", "4.0.0"))

    testImplementation(kotlin("test-js"))
}


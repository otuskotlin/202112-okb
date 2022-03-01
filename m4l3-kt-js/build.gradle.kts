plugins {
    kotlin("js")
}

group = "ru.otus.okb.first"
version = "0.0.1"


repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

kotlin {
    js(IR) {
        useCommonJs()
        binaries.executable()
        browser {
            commonWebpackConfig {

            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }

        nodejs()
    }
}

dependencies {
    implementation(npm("array-sort", "1.0.0"))
    implementation(npm("decamelize", "4.0.0"))
    implementation(devNpm("html-webpack-plugin", "5.3.1"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")

    testImplementation(npm("mathjs", "7.2.0"))
    testImplementation(kotlin("test-js"))
}


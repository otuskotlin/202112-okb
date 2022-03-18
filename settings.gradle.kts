rootProject.name = "first_kt_app"
include("m2l3-dsl")
include("m3l2-coroutines")

pluginManagement {
    val kotlinVersion: String by settings
    val composeVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        kotlin("js") version kotlinVersion apply false

        kotlin("multiplatform") version kotlinVersion apply false
        id("org.jetbrains.compose") version composeVersion apply false
    }
}
include("m2l4-practice-dsl")
include("m4l3-kt-js")
include("m5l2-compose-web")

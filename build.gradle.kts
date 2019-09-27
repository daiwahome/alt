plugins {
    application
    kotlin("jvm") version "1.3.50"
}

application {
    mainClassName = "com.github.daiwahome.alt.MainKt"
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.github.ajalt:clikt:2.1.0")
}

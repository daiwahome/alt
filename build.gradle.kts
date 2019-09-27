plugins {
    application
    kotlin("jvm") version "1.3.50"
}

application {
    mainClassName = "com.github.daiwahome.alt.AltApplicationKt"
}

repositories {
    jcenter()
}

dependencies {
    val cliktVersion = "2.1.0"
    val koinVersion = "2.0.1"

    implementation("com.github.ajalt:clikt:$cliktVersion")
    implementation("org.koin:koin-core:$koinVersion")
}

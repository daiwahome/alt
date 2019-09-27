plugins {
    application
    jacoco
    kotlin("jvm") version "1.3.50"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.3.50"
}

application {
    mainClassName = "com.github.daiwahome.alt.AltApplicationKt"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    val cliktVersion = "2.1.0"
    val klockVersion = "1.7.3"
    val koinVersion = "2.0.1"
    val serializationRuntimeVersion = "0.13.0"

    implementation(kotlin("stdlib"))
    implementation("com.github.ajalt:clikt:$cliktVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationRuntimeVersion")
    implementation("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
    implementation("org.koin:koin-core:$koinVersion")

    // Test
    val assertkVersion = "0.20"
    val junitVersion = "5.5.2"

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
}

jacoco {
    toolVersion = "0.8.4"
    applyTo(tasks.run.get())
}

tasks.jacocoTestReport {
    reports {
        csv.isEnabled = false
        xml.isEnabled = false
    }
}

tasks.test {
    useJUnitPlatform()
}

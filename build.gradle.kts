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
    val coroutinesVersion = "1.3.2"
    val klockVersion = "1.7.3"
    val koinVersion = "2.0.1"
    val ktorVersion = "1.2.4"
    val serializationRuntimeVersion = "0.13.0"

    implementation(kotlin("stdlib"))
    implementation("com.github.ajalt:clikt:$cliktVersion")
    implementation("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationRuntimeVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.koin:koin-core:$koinVersion")

    // Test
    val assertkVersion = "0.20"
    val junitVersion = "5.5.2"

    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
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

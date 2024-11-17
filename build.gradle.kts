plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.8.1")
    testImplementation("io.kotest:kotest-framework-datatest:5.8.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

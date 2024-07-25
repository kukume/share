plugins {
    val kotlinVersion = "2.0.0"
    val ktorVersion = "2.3.12"
    kotlin("jvm") version kotlinVersion
    id("io.ktor.plugin") version ktorVersion
}

group = "me.kuku"
version = "1.0-SNAPSHOT"

val ktorVersion = "2.3.12"

repositories {
    maven("https://nexus.kuku.me/repository/maven-public/")
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-thymeleaf:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cio-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-config-yaml-jvm:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.1.2")
    implementation("org.mongodb:bson-kotlinx:5.1.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("me.kuku.share.ShareKt")
}

ktor {
    fatJar {
        archiveFileName.set("share.jar")
    }
}
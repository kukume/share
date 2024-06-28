plugins {
    val kotlinVersion = "2.0.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "me.kuku"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://nexus.kuku.me/repository/maven-public/")
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("me.kuku:utils:2.3.11.0")
    implementation("me.kuku:ktor-spring-boot-starter:2.3.11.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
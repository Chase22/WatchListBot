plugins {
    java
    kotlin("jvm") version "1.4.31"
}

group = "io.github.chase22"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.telegram:telegrambotsextensions:5.0.1.1")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("junit", "junit", "4.12")
}

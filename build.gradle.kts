plugins {
    java
    application
    kotlin("jvm") version "1.4.31"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("pl.allegro.tech.build.axion-release") version "1.13.2"
}

group = "io.github.chase22"
version = scmVersion.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.telegram:telegrambotsextensions:5.0.1.1")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("junit", "junit", "4.12")
}

application {
    mainClass.set("io.github.chase22.watchlist.ApplicationKt")
    mainClassName = mainClass.get()
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}

tasks.jar {
    enabled = false
}

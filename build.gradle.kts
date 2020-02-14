plugins {
    kotlin("jvm") version "1.3.60"
    application
}

group = "edu.illinois.web.danielz01"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
application {
    mainClassName = "Main"
}
tasks.withType<Test> {
    useJUnitPlatform()
}
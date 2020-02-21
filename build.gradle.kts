plugins {
    kotlin("jvm") version "1.3.60"
    application
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

group = "edu.illinois.web.danielz01"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    val ktorVersion = "1.3.0"
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")
    testImplementation("org.slf4j:slf4j-simple:1.7.26")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
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

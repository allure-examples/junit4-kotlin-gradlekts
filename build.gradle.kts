plugins {
    kotlin("jvm") version "1.9.23"
}

tasks.withType(Wrapper::class) {
    gradleVersion = "8.7"
}

group = "com.example.junit4"
version = "1.0-SNAPSHOT"

val allureVersion = "2.26.0"
val aspectJVersion = "1.9.22"
val kotlinVersion = "1.9.23"

kotlin {
    jvmToolchain(17)
}

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

tasks.test {
    ignoreFailures = true
    useJUnit()
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}

dependencies {
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
    
    testImplementation(platform("org.jetbrains.kotlin:kotlin-bom:$kotlinVersion"))
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit4")
    testImplementation("io.qameta.allure:allure-junit4-aspect")

    testImplementation("junit:junit:4.13.2")

    testImplementation("org.slf4j:slf4j-simple:2.0.12")
}

repositories {
    mavenCentral()
}

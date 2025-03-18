
plugins {
    java
    kotlin("jvm") version "2.0.21"
    alias(libs.plugins.springBoot)
    alias(libs.plugins.dependencyManagement)
}

group = "com.esteban.ms"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation(libs.bundles.spring)
    compileOnly(libs.lombok)
    developmentOnly(libs.devtools)
    runtimeOnly(libs.postgresql)
    annotationProcessor(libs.lombok)
    testImplementation(libs.test)
    testRuntimeOnly(libs.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

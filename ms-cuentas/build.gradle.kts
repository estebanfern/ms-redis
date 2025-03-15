
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

dependencies {
    implementation(libs.bundles.spring)
    compileOnly(libs.lombok)
    developmentOnly(libs.devtools)
    runtimeOnly(libs.postgresql)
    annotationProcessor(libs.lombok)
    testImplementation(libs.test)
    testRuntimeOnly(libs.junit)
    implementation(project(":ms-common"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

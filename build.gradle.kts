import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.kotlin)
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junitJupiter)
    testRuntimeOnly(libs.junitPlatformLauncher)
    testImplementation(libs.kotestAssertions)
}

tasks.test {
    useJUnitPlatform()
    reports.junitXml.required = false
}

// set Java version
tasks.withType<KotlinJvmCompile> {
    compilerOptions.freeCompilerArgs.add(libs.versions.java.map { "-Xjdk-release=$it" })
    compilerOptions.jvmTarget = libs.versions.java.map { JvmTarget.fromTarget(it) }
}
tasks.withType<JavaCompile> {
    options.release = libs.versions.java.map { it.toInt() }
}

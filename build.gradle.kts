import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.kotlin)
    `java-library`

    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junitJupiter)
    testRuntimeOnly(libs.junitPlatformLauncher)
    testImplementation(libs.kotestAssertions)
}

kotlin {
    explicitApi()
}

tasks.test {
    useJUnitPlatform()
    reports.junitXml.required = false
}

tasks.withType<Detekt> {
    basePath = rootProject.projectDir.absolutePath
    reports.html.required = true
    reports.sarif.required = true
}

// set Java version
tasks.withType<KotlinJvmCompile> {
    compilerOptions.freeCompilerArgs.add(libs.versions.java.map { "-Xjdk-release=$it" })
    compilerOptions.jvmTarget = libs.versions.java.map { JvmTarget.fromTarget(it) }
}
tasks.withType<JavaCompile> {
    options.release = libs.versions.java.map { it.toInt() }
}

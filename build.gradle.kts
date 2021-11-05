import java.util.Calendar
import java.util.TimeZone

plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}
val powerNukkitVersion: String by project
val kotlinVersion: String by project
val kotlinxSerializationVersion: String by project
val kotlinxCoroutinesVersion: String by project
val ktorVersion: String by project
val kotlinxCollectionsImmutableVersion: String by project
val kotlinxDateTimeVersion: String by project
val kamlVersion: String by project
val knbtVersion: String by project
val inlineLoggerVersion: String by project
val slf4jVersion: String by project
val jetbrainsAnnotationsVersion: String by project
val okioVersion: String by project
val log4jSlf4jImplVersion: String by project

group = "org.powernukkit.plugins"
val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))!!
version = "$kotlinVersion+0.1.0+${cal[Calendar.YEAR]}.${cal[Calendar.MONTH]}.${cal[Calendar.DAY_OF_MONTH]}-SNAPSHOT"

repositories {
    mavenCentral()
}

val included by configurations.creating

dependencies {
    implementation("org.powernukkit:powernukkit:$powerNukkitVersion")
    includedApi("org.slf4j:slf4j-api:$slf4jVersion")
    includedImplementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jSlf4jImplVersion") {
        exclude("org.apache.logging.log4j", "log4j-core")
        exclude("org.apache.logging.log4j", "log4j-api")
    }
    includedApi("org.jetbrains:annotations:$jetbrainsAnnotationsVersion")
    includedApi(kotlin("stdlib", kotlinVersion))
    includedApi(kotlin("stdlib-jdk7", kotlinVersion))
    includedApi(kotlin("stdlib-jdk8", kotlinVersion))
    includedApi(kotlin("reflect", kotlinVersion))
    includedApi(kotlinx("coroutines-core", kotlinxCoroutinesVersion))
    includedApi(kotlinx("serialization-core", kotlinxSerializationVersion))
    includedApi(kotlinx("serialization-json", kotlinxSerializationVersion))
    includedApi(kotlinx("serialization-protobuf", kotlinxSerializationVersion))
    includedApi(kotlinx("serialization-cbor", kotlinxSerializationVersion))
    includedApi(kotlinx("serialization-properties", kotlinxSerializationVersion))
    includedApi("com.squareup.okio:okio:$okioVersion")
    includedApi("net.benwoodworth.knbt:knbt:$knbtVersion")
    includedApi("com.charleskorn.kaml:kaml:$kamlVersion") {
        exclude("org.snakeyaml", "snakeyaml-engine")
    }
    includedApi(kotlinx("datetime", kotlinxDateTimeVersion))
    includedApi(kotlinx("collections-immutable", kotlinxCollectionsImmutableVersion))
    includedApi("io.ktor:ktor-io:$ktorVersion")
    includedApi("io.ktor:ktor-utils:$ktorVersion")
    includedApi("io.ktor:ktor-network:$ktorVersion")
    includedApi("io.ktor:ktor-network-tls:$ktorVersion")
    includedApi("io.ktor:ktor-http:$ktorVersion")
    includedApi("io.ktor:ktor-http-cio:$ktorVersion")
    includedApi("io.ktor:ktor-client-core:$ktorVersion")
    includedApi("io.ktor:ktor-client-cio:$ktorVersion")
    includedApi("io.ktor:ktor-client-json:$ktorVersion")
    includedApi("io.ktor:ktor-client-gson:$ktorVersion") {
        exclude("com.google.code.gson", "gson")
    }
    includedApi("io.ktor:ktor-client-serialization:$ktorVersion")
    includedApi("com.michael-bull.kotlin-inline-logger:kotlin-inline-logger:$inlineLoggerVersion")
}

kotlin {
    explicitApi()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    shadowJar {
        configurations = listOf(included)
    }

    build {
        finalizedBy(shadowJar)
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn,kotlin.contracts.ExperimentalContracts"
        }
    }

    processResources {
        eachFile {
            filter<org.apache.tools.ant.filters.ReplaceTokens>(mapOf(
                "tokens" to mapOf(
                    "version" to project.version
                )
            ))
        }
    }
}

fun DependencyHandlerScope.includedApi(
    dependencyNotation: Any?,
) {
    requireNotNull(dependencyNotation)
    included(dependencyNotation)
    api(dependencyNotation)
}

fun DependencyHandlerScope.includedApi(
    dependencyNotation: String?,
) {
    requireNotNull(dependencyNotation)
    included(dependencyNotation)
    api(dependencyNotation)
}

fun DependencyHandlerScope.includedApi(
    dependencyNotation: String?,
    dependencyConfiguration: ExternalModuleDependency.()->Unit
) {
    requireNotNull(dependencyNotation)
    included(dependencyNotation, dependencyConfiguration)
    api(dependencyNotation, dependencyConfiguration)
}

fun DependencyHandlerScope.includedImplementation(
    dependencyNotation: String?,
    dependencyConfiguration: ExternalModuleDependency.()->Unit
) {
    requireNotNull(dependencyNotation)
    included(dependencyNotation, dependencyConfiguration)
    implementation(dependencyNotation, dependencyConfiguration)
}

fun kotlinx(name: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$name:$version"


///////////////////////////////////////////////////////////////////
// Some fancy functions to allow you to debug your plugin easily //
// Just run ./gradlew run -q to run PowerNukkit with your plugin //
// Or execute the "debug" task in debug mode with your IDE       //
///////////////////////////////////////////////////////////////////

tasks {
    register<JavaExec>("debug") {
        dependsOn("createDebugJar")
        group = "Execution"
        description = "Run PowerNukkit with your plugin in debug mode (without Watchdog Thread)"
        workingDir = file("run")
        systemProperties = mapOf("file.encoding" to "UTF-8", "disableWatchdog" to true)
        mainClass.set("cn.nukkit.Nukkit")
        standardInput = System.`in`
        classpath = sourceSets.main.get().runtimeClasspath
    }

    register<JavaExec>("run") {
        dependsOn("createDebugJar")
        group = "Execution"
        description = "Run PowerNukkit with your plugin"
        mainClass.set("cn.nukkit.Nukkit")
        workingDir = file("run")
        systemProperties = mapOf("file.encoding" to "UTF-8")
        standardInput = System.`in`
        classpath = sourceSets.main.get().runtimeClasspath
    }

    register<Jar>("createDebugJar") {
        dependsOn(classes)
        group = "Execution"
        description = "Creates a fake jar to make PowerNukkit load your plugin directly from the compiled classes"

        from(sourceSets.main.get().output.resourcesDir!!) {
            include("plugin.yml")
            include("nukkit.yml")
        }

        destinationDirectory.set(file("run/plugins"))
        archiveBaseName.set("__plugin_loader")
        archiveExtension.set("jar")
        archiveAppendix.set("")
        archiveClassifier.set("")
        archiveVersion.set("")
    }
}

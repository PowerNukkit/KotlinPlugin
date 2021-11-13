import java.net.URI
import java.util.Calendar
import java.util.TimeZone

plugins {
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.jetbrains.dokka") version "1.5.30"
    `maven-publish`
    signing
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
val ossrhUsername: String by project
val ossrhPassword: String by project

group = "org.powernukkit.plugins"
val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))!!
version = "$kotlinVersion+0.1.0+${cal[Calendar.YEAR]}.${cal[Calendar.MONTH]+1}.${cal[Calendar.DAY_OF_MONTH]}-SNAPSHOT"

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
    includedApi(ktor("io"))
    includedApi(ktor("utils"))
    includedApi(ktor("network"))
    includedApi(ktor("network-tls"))
    includedApi(ktor("http"))
    includedApi(ktor("http-cio"))
    includedApi(ktor("client-core"))
    includedApi(ktor("client-cio"))
    includedApi(ktor("client-auth"))
    includedApi(ktor("client-serialization"))
    includedApi(ktor("client-json"))
    includedApi(ktor("client-gson")) {
        exclude("com.google.code.gson", "gson")
    }
    includedApi("com.michael-bull.kotlin-inline-logger:kotlin-inline-logger:$inlineLoggerVersion")
}

kotlin {
    explicitApi()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    //withJavadocJar()
    withSourcesJar()
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

val dokkaHtmlJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml.get().outputDirectory)
}

publishing {
    repositories {
        maven {
            val local = false
            val releasesRepoUrl: URI
            val snapshotsRepoUrl: URI
            if (local) {
                releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
                snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            } else {
                releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }

            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "kotlin-plugin-lib"
            from(components["java"])
            artifact(dokkaHtmlJar)
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("PowerNukkit Kotlin Plugin Library")
                description.set("Provides Kotlin libs and some features for building Kotlin plugins")
                url.set("https://github.com/PowerNukkit/KotlinPlugin")
                inceptionYear.set("2021")
                packaging = "jar"
                issueManagement {
                    url.set("https://github.com/PowerNukkit/KotlinPlugin/issues")
                    system.set("GitHub")
                }
                organization {
                    name.set("PowerNukkit")
                    url.set("https://powernukkit.org")
                }
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("joserobjr")
                        name.set("José Roberto de Araújo Júnior")
                        email.set("joserobjr@powernukkit.org")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/PowerNukkit/KotlinPlugin.git")
                    developerConnection.set("scm:git:ssh://github.com/PowerNukkit/KotlinPlugin.git")
                    url.set("https://github.com/PowerNukkit/KotlinPlugin")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
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
fun ktor(name: String, version: String = ktorVersion) = "io.ktor:ktor-$name:$version"


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

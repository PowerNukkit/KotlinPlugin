# Kotlin Plugin
Provides Kotlin libs and some features for building awesome Kotlin plugins.

Can be used instead of [CreeperFace's KotlinLib](https://cloudburstmc.org/resources/kotlinlib.48/) (don't use together!)

## Included Libraries

### Kotlin
* [kotlin-stdlib](https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib)
* [kotlin-stdlib-jdk7](https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib)
* [kotlin-stdlib-jdk8](https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib)
* [kotlin-reflect](https://github.com/JetBrains/kotlin/tree/master/libraries/reflect)
* [kotlinx-coroutines-core](https://github.com/Kotlin/kotlinx.coroutines)
* [kotlinx-serialization-core](https://github.com/Kotlin/kotlinx.serialization)
* [kotlinx-serialization-json](https://github.com/Kotlin/kotlinx.serialization)
* [kotlinx-serialization-protobuf](https://github.com/Kotlin/kotlinx.serialization)
* [kotlinx-serialization-cbor](https://github.com/Kotlin/kotlinx.serialization)
* [kotlinx-serialization-properties](https://github.com/Kotlin/kotlinx.serialization)
* [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime)
* [kotlinx-collections-immutable](https://github.com/Kotlin/kotlinx.collections.immutable)
* [okio](https://square.github.io/okio/)
* [knbt](https://github.com/BenWoodworth/knbt)
* [kaml](https://github.com/charleskorn/kaml)
* [ktor-io](https://github.com/ktorio/ktor/tree/main/ktor-io)
* [ktor-utils](https://ktor.io/docs/servers-raw-sockets.html)
* [ktor-network](https://ktor.io/docs/servers-raw-sockets.html)
* [ktor-network-tls](https://ktor.io/docs/servers-raw-sockets.html)
* [ktor-http](https://ktor.io/docs/client.html)
* [ktor-http-cio](https://ktor.io/docs/client.html)
* [ktor-client-core](https://ktor.io/docs/client.html)
* [ktor-client-cio](https://ktor.io/docs/client.html)
* [ktor-client-auth](https://ktor.io/docs/auth.html)
* [ktor-client-json](https://ktor.io/docs/json.html)
* [ktor-client-gson](https://ktor.io/docs/json.html)
* [ktor-client-serialization](https://ktor.io/docs/json.html)
* [MichaelBull's kotlin-inline-logger](https://github.com/michaelbull/kotlin-inline-logger)

### Java
* [slf4j-api](http://www.slf4j.org/)
* [jetbrains-annotations](https://www.jetbrains.com/help/idea/annotating-source-code.html)


## How to use in your plugin

Add this dependency to your `plugin.yml` file (required):
```yaml
depend:
  - KotlinLib
```

Add a library dependency to your project using the examples bellow (recommended).

Make your plugin class extend `KotlinPluginBase` (optional).

Note: We haven't published to the maven central yet, but the snapshots are available at Sonatype OSS snapshots repository.

### Gradle (Kotlin DSL)
```kotlin
repositories {
    maven(url="https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation("org.powernukkit:powernukkit:1.5.1.0-PN")
    implementation("org.powernukkit.plugins:kotlin-plugin-lib:1.5.31+0.1.0+2021.10.5-SNAPSHOT")
}
```

### Gradle (Groovy DSL)
```groovy
repositories {
    maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
}

dependencies {
    implementation 'org.powernukkit:powernukkit:1.5.1.0-PN'
    implementation 'org.powernukkit.plugins:kotlin-plugin-lib:1.5.31+0.1.0+2021.10.5-SNAPSHOT'
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>sonatype-oss-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.powernukkit</groupId>
        <artifactId>powernukkit</artifactId>
        <version>1.5.1.0-PN</version>
    </dependency>
    <dependency>
        <groupId>org.powernukkit.plugins</groupId>
        <artifactId>kotlin-plugin-lib</artifactId>
        <version>1.5.31+0.1.0+2021.10.5-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Cloning and importing
1. Just do a normal `git clone https://github.com/PowerNukkit/KotlinPlugin.git` (or the URL of your own git repository)
2. Import the `build.gradle.kts` file with your IDE, it should do the rest by itself

## Running
1. Just do a normal `git clone https://github.com/PowerNukkit/KotlinPlugin.git` (or the URL of your own git repository)
2. `cd KotlinPlugin` (or the name of your project)
3. `./gradlew run`

## Debugging
1. Import the project into your IDE
2. Make your IDE run the `debug` gradle task in debug mode

### Debuging using IntelliJ IDEA
Import the project and do this:  
![](https://i.imgur.com/eJxjEX0.png)

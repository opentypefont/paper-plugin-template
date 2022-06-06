import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

group = project.property("group")!!
version = project.property("version")!!

var packageName = rootProject.name.replace("-", "")
extra.set("packageName", packageName)

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(sourceSets.main.get().output)

        from({
            configurations.runtimeClasspath.get().filter {
                it.name.endsWith("jar")
            }.map {
                zipTree(it)
            }
        })

        from({
            configurations.compileClasspath.get().filter {
                it.isDirectory
            }.map {
                zipTree(it)
            }
        })
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
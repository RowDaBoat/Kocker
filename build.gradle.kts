plugins {
    kotlin("jvm") version "1.9.22"
    id("maven-publish")
    `maven-publish`
}

val groupName = "com.rowdaboat"
val artifactName = "kocker"
val libVersion = "1.0.0"

group = groupName
version = libVersion

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("kocker") { from(components["kotlin"]) }
    }

    repositories {
        mavenLocal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/RowDaBoat/kocker")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

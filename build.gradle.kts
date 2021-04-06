plugins {
    `java-library`
    checkstyle
    id("io.freefair.lombok") version("5.3.0")
}

group = "io.github.jenyaatnow"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "checkstyle")
    apply(plugin = "io.freefair.lombok")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
    }

    tasks.test {
        useJUnitPlatform()
    }

    checkstyle {
        toolVersion = "8.41.1"
    }
}

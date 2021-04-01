plugins {
    java
    id("io.freefair.lombok") version("5.3.0")
}

group = "com.validator4j"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "io.freefair.lombok")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.1")
        testImplementation("org.jmockit:jmockit:1.49")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

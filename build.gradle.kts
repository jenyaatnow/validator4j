plugins {
    java
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
}

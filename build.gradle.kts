plugins {
    `java-library`
    checkstyle
    id("io.freefair.lombok") version("5.3.0")
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "checkstyle")
    apply(plugin = "io.freefair.lombok")

    group = "io.github.jenyaatnow"
    version = "0.1.0"

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

val publicModules = listOf("validator4j-apt", "validator4j-codegen", "validator4j-core", "validator4j-util")
configure(subprojects.filter { it.name in publicModules }) {
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    java {
        withJavadocJar()
        withSourcesJar()
    }

    tasks.javadoc {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])

                versionMapping {
                    usage("java-api") {
                        fromResolutionOf("runtimeClasspath")
                    }
                    usage("java-runtime") {
                        fromResolutionResult()
                    }
                }

                pom {
                    name.set("Validator4j")
                    description.set("Java bean validation library designed to perform validation" +
                        " in the most natural way using code generation capabilities.")
                    url.set("https://github.com/jenyaatnow/validator4j")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            name.set("Evgeniy Surin")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/jenyaatnow/validator4j.git")
                        developerConnection.set("scm:git:ssh://github.com/jenyaatnow/validator4j.git")
                        url.set("https://github.com/jenyaatnow/validator4j")
                    }
                }
            }
        }

        repositories {
            maven {
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl

                credentials {
                    username = "${property("sonatypeUsername")}"
                    password = "${property("sonatypePassword")}"
                }
            }
        }
    }

    signing {
        sign(publishing.publications["mavenJava"])
    }
}

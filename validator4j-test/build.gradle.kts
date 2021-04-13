dependencies {
    implementation(project(":validator4j-core"))
    annotationProcessor(project(":validator4j-apt"))
}

tasks.register<Copy>("copyGeneratedFiles") {
    from("$buildDir/generated/sources/annotationProcessor/java/main")
    include("*.java")
    into("$buildDir/resources/test/generated")
}

tasks.getByPath("test").dependsOn("copyGeneratedFiles")

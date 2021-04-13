dependencies {
    implementation("com.google.auto.service:auto-service-annotations:1.0")
    annotationProcessor("com.google.auto.service:auto-service:1.0")

    implementation(project(":validator4j-core"))
    implementation(project(":validator4j-codegen"))
}

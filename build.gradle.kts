plugins {
    id("java")
    id("application")
}
repositories {
    mavenCentral()
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
application {
    mainClass.set("it.unibo.TestTransactions") // Eseguiremo solo i test
}
tasks.withType<Test> {
    useJUnitPlatform()
}
plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.7")

    // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
    implementation("org.xerial:sqlite-jdbc:3.49.1.0")

    // https://mvnrepository.com/artifact/io.javalin/javalin
    implementation("io.javalin:javalin:6.3.0")
    //helper dependency for javalin - the logger that javalin uses
    implementation("org.slf4j:slf4j-simple:2.0.16")
    //helper dependency for javalin - the json parser (translator) that javalin uses
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

}

tasks.test {
    useJUnitPlatform()
}
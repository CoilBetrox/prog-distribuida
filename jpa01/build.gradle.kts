plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:6.4.1.Final")
    implementation("com.h2database:h2:2.2.224")
    compileOnly("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets{
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}
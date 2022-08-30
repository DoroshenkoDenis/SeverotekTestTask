plugins {
    id("java")
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    compileTestJava {
        options.encoding = "UTF-8"
    }
}


group = "ru.doroshenko.testTask"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.4.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.3.0")
    implementation ("org.slf4j:slf4j-api:2.0.0")
    testImplementation("org.slf4j:slf4j-log4j12:2.0.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
plugins {
    `java-library`
    `maven-publish`
    //checkstyle
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}


dependencies {
    api("org.springframework.boot:spring-boot-starter-web:2.7.10") {
        exclude("org.springframework.boot:spring-boot-starter-logging")
        exclude("org.springframework.boot:spring-boot-starter-json")
    }
    api("org.springframework.boot:spring-boot-starter-log4j2:2.7.10")
    api("org.springframework.boot:spring-boot-starter-data-jpa:2.7.10")
    api("org.springframework.boot:spring-boot-starter-validation:2.7.10")
    api("org.springframework.boot:spring-boot-starter-security:2.7.10")
    api("org.springframework.boot:spring-boot-starter-actuator:2.7.10")
    api("org.springframework.boot:spring-boot-starter-mail:2.7.10")
    api("com.auth0:java-jwt:4.3.0")
    api("org.mapstruct:mapstruct:1.5.3.Final")
    api("org.mapstruct:mapstruct-processor:1.5.3.Final")
    api("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    api("org.apache.commons:commons-lang3:3.12.0")
    api("org.springdoc:springdoc-openapi-ui:1.6.15")
    api("org.apache.logging.log4j:log4j-api:2.20.0")
    api("org.apache.logging.log4j:log4j-core:2.20.0")
    api("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
    runtimeOnly("org.postgresql:postgresql:42.3.8")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation("com.puppycrawl.tools:checkstyle:10.9.1")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.paypal.sdk:rest-api-sdk:1.4.2")
    implementation ("com.stripe:stripe-java:22.0.0")
    implementation ("com.google.api-client:google-api-client:1.30.10")
    implementation("com.cloudinary:cloudinary-http44:1.35.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

}

configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
}

//apply(plugin = "checkstyle")

/*tasks.withType<Checkstyle>().configureEach {

    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}*/

tasks.withType<Test> {
    useJUnitPlatform()
}
/*
checkstyle {
    configFile = file("config/checkstyle/checkstyle.xml")
    toolVersion = "10.9.3"
    reportsDir = file("report/")
    isIgnoreFailures = false
    maxWarnings = 0

}*/

group = "com.FPTU"
version = "1.0.0"
description = "DrawingCourseSellingWeb"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}




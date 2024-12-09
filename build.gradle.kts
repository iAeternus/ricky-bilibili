group = "org.ricky"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    id("application")
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass.set("org.ricky.BilibiliBackendApplication")
    applicationDefaultJvmArgs = listOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005")
}

repositories {
    mavenLocal()
    maven { setUrl("https://maven.aliyun.com/repository/central") }
    maven { setUrl("https://plugins.gradle.org/m2/") }
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }

    create("apiTestImplementation").extendsFrom(configurations["testImplementation"])
    create("apiTestRuntimeOnly").extendsFrom(configurations["testRuntimeOnly"])

    all {
        exclude(group = "junit", module = "junit")
        exclude(group = "org.assertj", module = "assertj-core")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.retry:spring-retry")
    implementation("io.micrometer:micrometer-tracing-bridge-otel:1.0.0")

    // Mysql
    implementation("com.mysql:mysql-connector-j")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")

    // Others
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("net.javacrumbs.shedlock:shedlock-spring:4.40.0")
    implementation("org.ansj:ansj_seg:5.1.6")
    implementation("org.apache.directory.studio:org.apache.commons.codec:1.8")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.25")
    implementation("com.google.guava:guava:32.0.1-jre")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:spring-mock-mvc")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core:4.9.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.9.0")
    testImplementation("com.apifan.common:common-random:1.0.18")
    testImplementation("org.apache.commons:commons-pool2:2.11.1")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
}

sourceSets {
    create("apiTest") {
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Test>("apiTest") {
    description = "Run API tests."
    group = "verification"
    testClassesDirs = sourceSets["apiTest"].output.classesDirs
    classpath = sourceSets["apiTest"].runtimeClasspath
    shouldRunAfter(tasks.named("test"))
}

tasks.named("check") {
    dependsOn(tasks.named("apiTest"))
}


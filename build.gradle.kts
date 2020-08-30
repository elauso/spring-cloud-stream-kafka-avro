import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    idea
    id("org.springframework.boot") version "2.1.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.commercehub.gradle.plugin.avro") version "0.9.1"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("kapt") version "1.3.61"
}

group = "net.elau.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

extra["springCloudVersion"] = "Greenwich.SR1"
extra["kafkaAvroSerializerVersion"] = "4.0.0"
extra["mapstructVersion"] = "1.4.0.Beta3"
extra["avroVersion"] = "1.8.1"
extra["h2databaseVersion"] = "1.4.200"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-schema")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("io.confluent:kafka-avro-serializer:${property("kafkaAvroSerializerVersion")}")
    implementation("org.apache.avro:avro:${property("avroVersion")}")
    implementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")
    kapt("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
    runtimeOnly("com.h2database:h2:${property("h2databaseVersion")}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

sourceSets["main"].java {
    srcDir("build/generated-main-avro-java")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

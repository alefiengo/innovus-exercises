plugins {
    kotlin("jvm") version "2.1.10"
    id("com.google.protobuf") version "0.9.4"
    application
}

group = "dev.alefiengo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-netty-shaded:1.70.0")
    implementation("io.grpc:grpc-protobuf:1.70.0")
    implementation("io.grpc:grpc-stub:1.70.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation(kotlin("test"))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.0"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.70.0"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("UserServerKt")
}

tasks.named<JavaExec>("run") {
    args = listOf()
}
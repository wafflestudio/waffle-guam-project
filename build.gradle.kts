
plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.spring") version "1.4.20"
    kotlin("plugin.jpa") version "1.4.20" apply false
   // kotlin("kapt") version "1.4.20"
}

group = "waffle.guam"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        jcenter()
    }
}

subprojects{
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin( "kotlin-allopen")
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        // map-struct
        // map-struct Annotation Processor
        //implementation("org.mapstruct:mapstruct:1.3.0.Final")
        //kapt("org.mapstruct:mapstruct-processor:1.3.0.Final")
        //kaptTest("org.mapstruct:mapstruct-processor:1.3.0.Final")
    }
}

//
//buildscript{
//    dependencies {
//        classpath("plugins/build/libs/plugin.jar")
//    }
//}

plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
}


val getGitTag = {
    try {
        ProcessBuilder("git", "describe", "--tags").start().inputStream.bufferedReader().readText().trim()
            .ifEmpty { "unknown" }
    }catch (e: Exception){
        "unknown"
    }
}

group = "com.hirrao"
version = getGitTag()
val springBootVersion = "3.4.1"
val kotestVersion = "5.9.1"
val kotlinVersion = "2.1.0"
repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    implementation("io.jsonwebtoken:jjwt:0.12.6")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.5")
    implementation("com.alibaba:druid-spring-boot-3-starter:1.2.18")
    //implementation("com.aliyun:dysmsapi20170525:3.0.0")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.4.7")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.3.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation("org.xerial:sqlite-jdbc:3.46.1.3")

    implementation(kotlin("stdlib:$kotlinVersion"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

/*tasks.register<Copy>("cacheToLocalMavenRepository") {
    from(File(gradle.gradleUserHomeDir, "caches/modules-2/files-2.1"))
    into(repositories.mavenLocal().url)
    eachFile {
        val parts = this.path.split("/")
        this.path = "${parts[0]}/${parts[1].replace('.', '/')}/${parts[2]}/${parts[4]}"
    }
    includeEmptyDirs = false
}*/

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

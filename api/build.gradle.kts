plugins {
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.21"
}

group = "com.hirrao"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.jsonwebtoken:jjwt:0.12.6")
    implementation("org.mybatis:mybatis:3.5.16")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.5")
    implementation("com.alibaba:druid-spring-boot-3-starter:1.2.18")
    //implementation("com.aliyun:dysmsapi20170525:3.0.0")
    implementation("org.springframework.data:spring-data-redis:3.3.3")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.4.7")

    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    runtimeOnly("org.xerial:sqlite-jdbc:3.46.1.3")

    implementation(kotlin("stdlib-jdk8"))
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

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
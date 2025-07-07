plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
}

group = "com.hirrao"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.redis)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.jjwt.api)
    implementation(libs.druid.spring.boot.starter)
    implementation(libs.mybatis.plus.starter)
    implementation(libs.mybatis.plus.jsqlparser)
    implementation(libs.dysmsapi20170525)
    implementation(libs.pagehelper.starter)
    implementation(libs.mapstruct)

    testImplementation(libs.mybatis.plus.starter.test)
    testImplementation(libs.spring.boot.starter.test)

    compileOnly(libs.lombok)

    runtimeOnly(libs.mysql.connector)

    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstruct.processor)
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
    jvmArgs("--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED",
        "--add-opens=java.base/java.lang=ALL-UNNAMED")
    useJUnitPlatform()
}

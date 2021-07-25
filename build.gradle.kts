import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Locale

plugins {
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
}

group = "studio.hwjames"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

var webappDir = "$projectDir/src/main/front"

sourceSets {
    main {
        resources {
            srcDirs("$webappDir/build", "$projectDir/src/main/resources")
        }
    }
}

// region DuplicatesStrategy Error
inline fun <reified C> Project.configure(name: String, configuration: C.() -> Unit) {
    (this.tasks.getByName(name) as C).configuration()
}

configure<ProcessResources>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.WARN
    dependsOn("buildReact")
}

tasks.register("jvmProcessResources", Copy::class) {
    duplicatesStrategy = DuplicatesStrategy.WARN
}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INHERIT }
// endregion

tasks.register("buildReact", Exec::class) {
    dependsOn("installReact")
    workingDir(webappDir)
    inputs.dir(webappDir)
    group = BasePlugin.BUILD_GROUP

    if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
        commandLine("yarn.cmd", "run", "build")
    } else {
        commandLine( "yarn", "run", "build")
    }
}

tasks.register("installReact", Exec::class) {
    workingDir(webappDir)
    inputs.dir(webappDir)
    group = BasePlugin.BUILD_GROUP

    if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
        commandLine("yarn.cmd", "audit", "fix")
        commandLine("yarn.cmd", "install")
    } else {
        commandLine("yarn", "audit", "fix")
        commandLine("yarn", "install")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

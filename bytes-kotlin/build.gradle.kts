import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.JavadocJar
import dev.detekt.gradle.Detekt
import dev.detekt.gradle.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.dokka.javadoc)
    alias(libs.plugins.maven.publish)
    id("signing")
}

group = Maven.GROUP_ID
version = Maven.VERSION

dependencies {
    implementation(platform(libs.kotlin.bom))
    implementation(libs.kotlin.stdlib)

    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
}

detekt {
    parallel = true
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
    config.from(rootDir.resolve("config/detekt.yml"))
}

java {
    toolchain {
        sourceCompatibility = Build.JvmTarget
        targetCompatibility = Build.JvmTarget
    }
}

kotlin {
    compilerOptions {
        optIn = listOf("kotlin.ExperimentalUnsignedTypes", "kotlin.ExperimentalStdlibApi")
        javaParameters = true
        jvmTarget = JvmTarget.fromTarget(Build.JvmTarget.toString())
    }
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required = false
        checkstyle.required = false
        sarif.required = true
        markdown.required = true
    }
}

tasks.withType<Test>().configureEach {
    systemProperties = System.getProperties().asIterable().associate { it.key.toString() to it.value }
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        events("passed", "skipped", "failed")
    }
}

signing {
    isRequired = true
    useGpgCmd()
    sign(publishing.publications)
}

mavenPublishing {
    configure(KotlinJvm(
        javadocJar = JavadocJar.Dokka("dokkaGeneratePublicationJavadoc"),
        sourcesJar = true,
    ))

    publishToMavenCentral()

    coordinates(Maven.GROUP_ID, Maven.ARTIFACT_ID, Maven.VERSION)

    pom {
        name = Maven.ARTIFACT_ID
        description = Maven.DESCRIPTION
        url = "https://github.com/${Maven.GITHUB_REPOSITORY}/"
        licenses {
            license {
                name = Maven.LICENSE_NAME
                url = Maven.LICENSE_URL
                distribution = Maven.LICENSE_DIST
            }
        }
        developers {
            developer {
                id = Maven.DEVELOPER_ID
                name = Maven.DEVELOPER_NAME
                url = Maven.DEVELOPER_URL
            }
        }
        scm {
            url = "https://github.com/${Maven.GITHUB_REPOSITORY}/"
            connection = "scm:git:git://github.com/${Maven.GITHUB_REPOSITORY}.git"
            developerConnection = "scm:git:ssh://git@github.com/${Maven.GITHUB_REPOSITORY}.git"
        }
    }
}

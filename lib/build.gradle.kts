import com.android.build.api.withAndroid
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotest)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.maven.publish)
    id("signing")
}

group = "jp.co.gahojin.bytes-kotlin"
version = "2026.4.1"

kotlin {
    jvm()
    android {
        namespace = "jp.co.gahojin.bytes"
        compileSdk = 36
        minSdk = 23

        withJava()
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }

        packaging {
            resources.pickFirsts.addAll(arrayOf("META-INF/AL2.0", "META-INF/LGPL2.1"))
        }
    }
    iosArm64()
    iosSimulatorArm64()
    macosArm64()
    linuxX64()

    @Suppress("OPT_IN_USAGE")
    applyDefaultHierarchyTemplate {
        common {
            group("commonJvm") {
                withAndroid()
                withJvm()
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlinx.serialization.core)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.property)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotest.runner.junit5)
            }
        }
    }

    targets.all {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    optIn.add("kotlin.ExperimentalUnsignedTypes")
                    freeCompilerArgs.add("-Xexpect-actual-classes")
                }
            }
        }
    }
}

detekt {
    parallel = true
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
    config.from(rootDir.resolve("config/detekt.yml"))
}

tasks.named<Test>("jvmTest") {
    useJUnitPlatform()
    filter {
        isFailOnNoMatchingTests = false
    }
}

signing {
    isRequired = project.hasProperty("signingInMemoryKey") || providers.environmentVariable("ORG_GRADLE_PROJECT_signingInMemoryKey").isPresent
}

mavenPublishing {
    configure(KotlinMultiplatform(
        javadocJar = JavadocJar.Dokka("dokkaGenerateHtml"),
        sourcesJar = SourcesJar.Sources(),
    ))

    publishToMavenCentral()
    if (project.hasProperty("signingInMemoryKey") || providers.environmentVariable("ORG_GRADLE_PROJECT_signingInMemoryKey").isPresent) {
        signAllPublications()
    }

    coordinates(group.toString(), "bytes-kotlin", version.toString())

    pom {
        name ="bytes-kotlin"
        description = "Bytes Utilities for Kotlin"
        url = "https://github.com/gahojin/bytes-kotlin/"
        licenses {
            license {
                name = "Apache-2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "gahojin"
                name = "GAHOJIN, Inc."
                url = "https://github.com/gahojin/"
            }
        }
        scm {
            url = "https://github.com/gahojin/bytes-kotlin/"
            connection = "scm:git:git://github.com/gahojin/bytes-kotlin.git"
            developerConnection = "scm:git:ssh://git@github.com/gahojin/bytes-kotlin.git"
        }
    }
}

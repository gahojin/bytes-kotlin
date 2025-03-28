plugins {
    id("jp.co.gahojin.refreshVersions") version "0.1.1"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "bytes-kotlin"
include("bytes-kotlin")

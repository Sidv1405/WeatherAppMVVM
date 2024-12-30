// settings.gradle
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")  // Jitpack repository for plugins and libraries
//        maven ("https://jcenter.bintray.com/" )
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")  // Jitpack repository for resolving dependencies
//        maven ("https://jcenter.bintray.com/" )
    }
}

rootProject.name = "WeatherApp"
include(":app")

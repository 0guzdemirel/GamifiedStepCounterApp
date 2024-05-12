pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            setUrl("https://jitpack.io")
        }
        flatDir {
            dirs("libs")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
        jcenter()
        flatDir {
            dirs("libs")
        }
    }
}

rootProject.name = "GamifiedStepCounterApp"
include(":app")

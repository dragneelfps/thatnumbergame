import org.jetbrains.kotlin.gradle.targets.js.webpack.*

plugins {
  kotlin("multiplatform") version "1.4.10"
  application
}
group = "io.github.dragneelfps"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
  maven("https://dl.bintray.com/kotlin/ktor")
  maven("https://dl.bintray.com/kotlin/kotlinx")
  maven("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
}
kotlin {
  jvm("backend") {
    compilations.all {
      kotlinOptions.jvmTarget = "1.8"
    }
    withJava()
  }
  js("frontend") {
    browser {
      binaries.executable()
      webpackTask {
        cssSupport.enabled = true
      }
      runTask {
        cssSupport.enabled = true
        devServer = devServer?.copy(
          port = 3000,
          proxy = mapOf("*" to "http://localhost:8080")
        )
      }
      testTask {
        useKarma {
          useChromeHeadless()
          webpackConfig.cssSupport.enabled = true
        }
      }
    }
  }
  sourceSets {
    val commonMain by getting
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
    val backendMain by getting {
      dependencies {
        implementation("io.ktor:ktor-server-netty:1.4.0")
        implementation("ch.qos.logback:logback-classic:1.2.3")
      }
    }
    val backendTest by getting {
      dependencies {
        implementation(kotlin("test-junit5"))
      }
    }
    val frontendMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
        implementation("org.jetbrains:kotlin-react:16.13.1-pre.110-kotlin-1.4.10")
        implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.110-kotlin-1.4.10")
        implementation("org.jetbrains:kotlin-styled:1.0.0-pre.110-kotlin-1.4.10")
      }
    }
    val frontendTest by getting {
      dependencies {
        implementation(kotlin("test-js"))
      }
    }
  }
}

application {
  mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks {
  withType<KotlinWebpack> {
    outputFileName = "main.js"
  }
  val frontendBrowserProductionWebpack by getting(KotlinWebpack::class)
  val backendProcessResources by getting(Copy::class) {
    dependsOn(frontendBrowserProductionWebpack)
    from(frontendBrowserProductionWebpack.destinationDirectory) {
      exclude("*.map")
      into("WEB-INF")
    }
  }
}

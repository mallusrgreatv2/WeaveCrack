plugins {
    java
    id("com.github.weave-mc.weave-gradle") version "649dba7468"
    kotlin("jvm")
}

group = "com.github.mallusrgreat"
version = "1.0"

minecraft.version("1.8.9")

repositories {
    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven/")
    mavenCentral()
}

dependencies {
    compileOnly("com.github.weave-mc:weave-loader:v0.2.4")

    compileOnly("org.spongepowered:mixin:0.8.5")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.compileJava {
    options.release.set(17)
}
kotlin {
    jvmToolchain(17)
}
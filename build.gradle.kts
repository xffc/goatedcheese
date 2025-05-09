import groovy.json.JsonSlurper
import java.net.URI

plugins {
    `maven-publish`
    kotlin("jvm")
    id("fabric-loom")
    id("me.modmuss50.mod-publish-plugin")
    id("de.undercouch.download")
}

class ModData {
    val id = property("mod.id").toString()
    val name = property("mod.name").toString()
    val version = property("mod.version").toString()
    val group = property("mod.group").toString()
}

class ModDependencies {
    operator fun get(name: String) = property("deps.$name").toString()
}

val mod = ModData()
val deps = ModDependencies()
val mcVersion = stonecutter.current.version
val mcDep = property("mod.mc_dep").toString()

version = "${mod.version}+$mcVersion"
group = mod.group
base { archivesName.set(mod.id) }

loom {
    fabricApi.configureDataGeneration {
        client = true
    }

    runs {
        remove(get("server"))

        all {
            runDir = "run"
        }
    }
}

repositories {
    fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
        forRepository { maven(url) { name = alias } }
        filter { groups.forEach(::includeGroup) }
    }
    strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
}

fun <T> get(url: String): T =
    JsonSlurper().parseText(URI(url).toURL().readText()) as T

enum class ModrinthVersionType { RELEASE, BETA, ALPHA, NULL; }
data class ModrinthVersionFile(val url: String, val filename: String)
data class ModrinthModVersion(val name: String, val version: String, val type: ModrinthVersionType, val files: List<ModrinthVersionFile>)

tasks.register("downloadRunMods") {
    fun download(name: String) {
        val versions = get<List<Map<String, *>>>("https://api.modrinth.com/v2/project/$name/version?game_versions=[%22$mcVersion%22]")
            .map { ModrinthModVersion(
                it["name"].toString(),
                it["version_number"].toString(),
                ModrinthVersionType.valueOf(it["version_type"].toString().uppercase()),
                (it["files"] as List<Map<String, *>>)
                    .map { f -> ModrinthVersionFile(f["url"].toString(), f["filename"].toString()) }
            ) }

        versions.firstOrNull()?.files?.forEach {
            download.run {
                src(it.url)
                dest("run/mods/${it.filename}")
                overwrite(true)
            }
        }
    }

    group = "other"

    doLast {
        download("fabric-api")
        download("fabric-language-kotlin")
    }
}

dependencies {
    fun fapi(vararg modules: String) = modules.forEach {
        modImplementation(fabricApi.module(it, deps["fabric_api"]))
    }

    minecraft("com.mojang:minecraft:$mcVersion")
    mappings("net.fabricmc:yarn:$mcVersion+build.${deps["yarn_build"]}:v2")
    modImplementation("net.fabricmc:fabric-loader:${deps["fabric_loader"]}")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.2+kotlin.${kotlin.coreLibrariesVersion}")

    fapi(
        // Add modules from https://github.com/FabricMC/fabric
        "fabric-lifecycle-events-v1",
        "fabric-item-group-api-v1",
        "fabric-object-builder-api-v1",
        "fabric-data-generation-api-v1"
    )
}

loom {
    decompilers {
        get("vineflower").apply { // Adds names to lambdas - useful for mixins
            options.put("mark-corresponding-synthetics", "1")
        }
    }

    runConfigs.all {
        ideConfigGenerated(true)
        vmArgs("-Dmixin.debug.export=true")
    }
}

java {
    withSourcesJar()
    val java = if (stonecutter.eval(mcVersion, ">=1.20.6")) JavaVersion.VERSION_21 else JavaVersion.VERSION_17
    targetCompatibility = java
    sourceCompatibility = java
}

tasks.processResources {
    inputs.property("id", mod.id)
    inputs.property("name", mod.name)
    inputs.property("version", mod.version)
    inputs.property("mcdep", mcDep)

    val map = mapOf(
        "id" to mod.id,
        "name" to mod.name,
        "version" to mod.version,
        "mcdep" to mcDep,
        "fabriclanguagekotlin" to "1.13.2+kotlin.${kotlin.coreLibrariesVersion}"
    )

    filesMatching("fabric.mod.json") { expand(map) }
}

tasks.register<Copy>("buildAndCollect") {
    group = "build"
    from(tasks.remapJar.get().archiveFile)
    into(rootProject.layout.buildDirectory.file("libs/${mod.version}"))
    dependsOn("build")
}

publishMods {
    file = tasks.remapJar.get().archiveFile
    additionalFiles.from(tasks.remapSourcesJar.get().archiveFile)
    displayName = "${mod.name} ${mod.version} for $mcVersion"
    version = mod.version
    changelog = rootProject.file("CHANGELOG.md").readText()
    type = STABLE
    modLoaders.add("fabric")

    dryRun = providers.environmentVariable("MODRINTH_TOKEN")
        .getOrNull() == null || providers.environmentVariable("CURSEFORGE_TOKEN").getOrNull() == null

    modrinth {
        projectId = property("publish.modrinth").toString()
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        minecraftVersions.add(mcVersion)
        requires {
            slug = "fabric-api"
        }
    }

    curseforge {
        projectId = property("publish.curseforge").toString()
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        minecraftVersions.add(mcVersion)
        requires {
            slug = "fabric-api"
        }
    }
}
/*
publishing {
    repositories {
        maven("...") {
            name = "..."
            credentials(PasswordCredentials::class.java)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "${property("mod.group")}.${mod.id}"
            artifactId = mod.version
            version = mcVersion

            from(components["java"])
        }
    }
}
*/
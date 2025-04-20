plugins {
    id("dev.kikugie.stonecutter")
    kotlin("jvm") version "2.1.20" apply false
    id("fabric-loom") version "1.9-SNAPSHOT" apply false
    id("me.modmuss50.mod-publish-plugin") version "0.7.+" apply false
    id("de.undercouch.download") version "5.+" apply false
}
stonecutter active "1.21.4" /* [SC] DO NOT EDIT */

// Builds every version into `build/libs/{mod.version}/`
stonecutter registerChiseled tasks.register("chiseledBuild", stonecutter.chiseled) {
    group = "project"
    ofTask("buildAndCollect")
}

/*
// Publishes every version
stonecutter registerChiseled tasks.register("chiseledPublishMods", stonecutter.chiseled) {
    group = "project"
    ofTask("publishMods")
}
*/

stonecutter parameters {
    /*
    See src/main/java/com/example/TemplateMod.java
    and https://stonecutter.kikugie.dev/
    */
    // Swaps replace the scope with a predefined value
    swap("mod_version", "\"${property("mod.version")}\";")
    // Constants add variables available in conditions
    const("release", property("mod.id") != "template")
    // Dependencies add targets to check versions against
    // Using `node.property()` in this block gets the versioned property
    dependency("fapi", node!!.property("deps.fabric_api").toString())
}

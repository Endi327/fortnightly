include(
        ":app",
        ":core",
        ":data",
        ":domain",
        ":news",
        ":common",
        ":resources"
)
plugins {
    id("de.fayard.refreshVersions") version "0.30.2"
}
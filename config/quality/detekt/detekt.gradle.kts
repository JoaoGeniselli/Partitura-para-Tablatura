plugins {
    id("io.gitlab.arturbosch.detekt").version("1.16.0")
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$projectDir/config/quality/detekt/detekt.yml")
    baseline = file("$projectDir/config/quality/detekt/baseline.xml")

    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
        sarif.enabled = false
    }
}

tasks.withType<Detekt>.configureEach {
    jvmTarget = "1.8"
}
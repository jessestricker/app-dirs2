package de.jessestricker.appdirs.util

import de.jessestricker.appdirs.Configuration

data class MockConfiguration(
    private val environmentVariables: Map<String, String> = emptyMap(),
    private val systemProperties: Map<String, String> = emptyMap(),
) : Configuration {
    override fun environmentVariable(name: String): String? {
        return environmentVariables[name]
    }

    override fun systemProperty(name: String): String? {
        return systemProperties[name]
    }
}

package de.jessestricker.appdirs

internal interface Configuration {
    fun environmentVariable(name: String): String?
    fun systemProperty(name: String): String?
}

internal class IllegalConfigurationError(message: String) : Error(message)

internal fun Configuration.standardEnvironmentVariable(name: String): String {
    return environmentVariable(name)
        ?: throw IllegalConfigurationError("The standard environment variable \"$name\" is not defined.")
}

internal fun Configuration.standardSystemProperty(name: String): String {
    return systemProperty(name)
        ?: throw IllegalConfigurationError("The standard system property \"$name\" is not defined.")
}

internal object SystemConfiguration : Configuration {
    override fun environmentVariable(name: String): String? {
        return System.getenv(name)
    }

    override fun systemProperty(name: String): String? {
        return System.getProperty(name)
    }
}

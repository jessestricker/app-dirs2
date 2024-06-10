package de.jessestricker.appdirs

import de.jessestricker.appdirs.util.MockConfiguration
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ConfigurationTest {
    private val configuration: Configuration = MockConfiguration(
        environmentVariables = mapOf("someName" to "someValue"),
        systemProperties = mapOf("someName" to "someValue"),
    )

    @Test
    fun `standard environment variable is returned when set`() {
        configuration.standardEnvironmentVariable("someName") shouldBe "someValue"
    }

    @Test
    fun `standard environment variable throws when not set`() {
        shouldThrow<IllegalConfigurationError> {
            configuration.standardEnvironmentVariable("someOtherName")
        }
    }

    @Test
    fun `standard system property is returned when set`() {
        configuration.standardSystemProperty("someName") shouldBe "someValue"
    }

    @Test
    fun `standard system property throws when not set`() {
        shouldThrow<IllegalConfigurationError> {
            configuration.standardSystemProperty("someOtherName")
        }
    }
}

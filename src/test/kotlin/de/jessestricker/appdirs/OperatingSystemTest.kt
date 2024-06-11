package de.jessestricker.appdirs

import de.jessestricker.appdirs.util.MockConfiguration
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OperatingSystemTest {
    @Test
    fun `current operating system is Linux`() {
        val configuration = MockConfiguration(systemProperties = mapOf("os.name" to "Linux"))

        OperatingSystem.current(configuration) shouldBe OperatingSystem.LINUX
    }

    @Test
    fun `current operating system is macOS`() {
        val configuration = MockConfiguration(systemProperties = mapOf("os.name" to "Mac OS X"))

        OperatingSystem.current(configuration) shouldBe OperatingSystem.MACOS
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "Windows 95", "Windows 98", "Windows Me",
            "Windows 2000", "Windows XP", "Windows 2003",
            "Windows Vista", "Windows 7", "Windows 8", "Windows 8.1",
            "Windows 10", "Windows 11",
        ]
    )
    fun `current operating system is Windows`(osName: String) {
        val configuration = MockConfiguration(systemProperties = mapOf("os.name" to osName))

        OperatingSystem.current(configuration) shouldBe OperatingSystem.WINDOWS
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "AIX", "foobar"])
    fun `current operating system is unsupported`(osName: String) {
        val configuration = MockConfiguration(systemProperties = mapOf("os.name" to osName))

        shouldThrow<UnsupportedOperatingSystemError> {
            OperatingSystem.current(configuration)
        }
    }
}

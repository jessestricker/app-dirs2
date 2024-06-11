package de.jessestricker.appdirs

internal enum class OperatingSystem {
    LINUX,
    MACOS,
    WINDOWS;

    companion object {
        fun current(configuration: Configuration): OperatingSystem {
            val name = configuration.standardSystemProperty("os.name")
            return when {
                // https://github.com/openjdk/jdk/blob/jdk-21%2B0/src/java.base/unix/native/libjava/java_props_md.c#L402
                name == "Linux" -> LINUX

                // https://github.com/openjdk/jdk/blob/jdk-21%2B0/src/java.base/macosx/native/libjava/java_props_macosx.c#L235
                name == "Mac OS X" -> MACOS

                // https://github.com/openjdk/jdk/blob/jdk-21%2B0/src/java.base/windows/native/libjava/java_props_md.c#L480-L577
                name.startsWith("Windows") -> WINDOWS

                else -> throw UnsupportedOperatingSystemError(name)
            }
        }
    }
}

internal class UnsupportedOperatingSystemError(name: String) :
    Error("The current operating system \"$name\" is unsupported.")

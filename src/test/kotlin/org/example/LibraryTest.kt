package org.example

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class LibraryTest {
    @Test
    fun someLibraryMethodReturnsTrue() {
        val classUnderTest = Library()
        classUnderTest.someLibraryMethod() shouldBe true
    }
}

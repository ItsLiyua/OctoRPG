package me.trqhxrd.octorpg.api.registry

import BaseTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RegistryTest : BaseTest() {

    @Test
    fun contains() {
        val registry = RegistryImpl()
        registry.add(RegisteredMock(1))
        assertTrue(registry.contains(1))
        assertFalse(registry.contains(2))
    }

    @Test
    fun add() {
        val registry = RegistryImpl()
        assertFalse(registry.contains(1))
        registry.add(RegisteredMock(1))
        assertTrue(registry.contains(1))
        assertThrows<IllegalArgumentException> { registry.add(RegisteredMock(1)) }
    }

    @Test
    fun getOrNull() {
        val registry = RegistryImpl()
        registry.add(RegisteredMock(1))
        assertNotNull(registry.getOrNull(1))
        assertNull(registry.getOrNull(2))
    }

    @Test
    fun get() {
        val registry = RegistryImpl()
        registry.add(RegisteredMock(1))
        assertNotNull(registry.get(1))
        assertThrows<NullPointerException> { registry.get(2) }
    }

    private class RegistryImpl : Registry<Int, RegisteredMock>()

    private class RegisteredMock(override val id: Int) : Registered<Int>
}
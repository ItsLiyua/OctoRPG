package me.trqhxrd.octorpg.api

import BaseTest
import org.bukkit.NamespacedKey
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertNotEquals

class OctoRPGTest : BaseTest() {

    @Test
    fun newKey() {
        val uuid = UUID.randomUUID()
        val expected = NamespacedKey(this.plugin, uuid.toString())
        assertEquals(expected, this.plugin.instance.newKey(uuid.toString()))
        assertNotEquals(expected, this.plugin.instance.newKey("other-key"))
    }
}
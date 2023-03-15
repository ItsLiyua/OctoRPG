package me.trqhxrd.octorpg

import BaseTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class MainTest : BaseTest() {

    @Test
    fun onEnable() {
        assertNotNull(this.server)
        assertEquals(this.server.pluginManager.getPlugin("OctoRPG"), this.plugin)
    }
}
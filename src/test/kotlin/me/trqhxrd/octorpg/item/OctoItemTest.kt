package me.trqhxrd.octorpg.item

import BaseTest
import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.attribute.Named
import mocks.MockAttribute
import org.bukkit.Material
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class OctoItemTest : BaseTest() {

    fun genItem(): OctoItem {
        val item = object : me.trqhxrd.octorpg.item.material.Material("debug", Material.DIAMOND) {
            override fun create(octoRPG: OctoRPG): OctoItem {
                return OctoItem(octoRPG, this)
            }
        }.create(this.plugin.instance)
        item.addAttribute(Named(this.plugin.instance, "debug-name"))
        return item
    }

    @Test
    fun attributes() {
        val item = this.genItem()
        assertEquals(1, item.attributes().size)
        item.addAttribute(MockAttribute(this.plugin.instance))
        assertEquals(2, item.attributes().size)
    }

    @Test
    fun hasAttribute() {
        val item = this.genItem()
        assertTrue(item.hasAttribute(Named(this.plugin.instance)))
        assertFalse(item.hasAttribute(MockAttribute(this.plugin.instance)))
        assertTrue(item.hasAttribute(Named(this.plugin.instance).id))
        assertFalse(item.hasAttribute(this.plugin.instance.newKey("some-attr")))
    }

    @Test
    fun addAttribute() {
        val item = this.genItem()
        item.removeAttribute(Named(this.plugin.instance).id)
        assertTrue(item.addAttribute(Named(this.plugin.instance)))
        assertFalse(item.addAttribute(Named(this.plugin.instance)))
    }

    @Test
    fun removeAttribute() {
        val item = this.genItem()
        val namedKey = Named(this.plugin.instance).id
        assertTrue(item.removeAttribute(namedKey))
        assertFalse(item.removeAttribute(namedKey))
    }

    @Test
    fun getAttribute() {
        val item = genItem()
        assertNotNull(item.getAttribute(Named(this.plugin.instance).id))
        assertThrows<NullPointerException> { item.getAttribute(MockAttribute(this.plugin.instance).id) }
    }

    @Test
    fun getAttributeOrNull() {
        val item = genItem()
        assertNotNull(item.getAttributeOrNull(Named(this.plugin.instance).id))
        assertNull(item.getAttributeOrNull(MockAttribute(this.plugin.instance).id))
    }
}
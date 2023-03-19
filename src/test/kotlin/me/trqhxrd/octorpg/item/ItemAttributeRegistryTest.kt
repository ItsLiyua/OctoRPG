package me.trqhxrd.octorpg.item

import BaseTest
import me.trqhxrd.octorpg.item.attribute.base.Named
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNull

class ItemAttributeRegistryTest : BaseTest() {

    @Test
    fun instantiate() {
        assertEquals(
            Named::class.qualifiedName,
            this.plugin.instance.attributeRegistry.instantiate(Named(this.plugin.instance).id)::class.qualifiedName
        )

        assertThrows<NullPointerException> {
            this.plugin.instance.attributeRegistry.instantiate(this.plugin.instance.newKey("random-attr"))
        }
    }

    @Test
    fun instantiateOrNull() {
        assertEquals(
            Named::class.qualifiedName,
            this.plugin.instance.attributeRegistry.instantiateOrNull(Named(this.plugin.instance).id)!!::class.qualifiedName
        )
        assertNull(this.plugin.instance.attributeRegistry.instantiateOrNull(this.plugin.instance.newKey("random-attr")))
    }
}
package me.trqhxrd.octorpg.item

import me.trqhxrd.octorpg.OctoRPG
import me.trqhxrd.octorpg.api.registry.Registry
import me.trqhxrd.octorpg.item.attribute.Named
import org.bukkit.NamespacedKey

class ItemAttributeRegistry(val octoRPG: OctoRPG, val registerDefaults: Boolean = true) :
    Registry<NamespacedKey, ItemAttribute.AttributeBuilder<out ItemAttribute>>() {

    init {
        if (this.registerDefaults) {
            this.add(Named.Builder(this.octoRPG))
        }
    }

    fun instantiate(key: NamespacedKey) = this.get(key).build()

    fun instantiateOrNull(key: NamespacedKey) = this.getOrNull(key)?.build()

    @Suppress("UNCHECKED_CAST")
    fun <T> instantiateAs(key: NamespacedKey, clazz: Class<T>) = this.instantiate(key) as T
}
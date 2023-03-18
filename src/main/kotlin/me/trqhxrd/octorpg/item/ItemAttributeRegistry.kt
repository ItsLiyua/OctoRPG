package me.trqhxrd.octorpg.item

import me.trqhxrd.octorpg.api.OctoRPG
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
}
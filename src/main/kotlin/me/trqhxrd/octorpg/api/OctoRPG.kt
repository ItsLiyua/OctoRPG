package me.trqhxrd.octorpg.api

import me.trqhxrd.octorpg.item.attribute.ItemAttributeRegistry
import me.trqhxrd.octorpg.item.material.MaterialRegistry
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class OctoRPG(val plugin: JavaPlugin, val settings: InitSettings = InitSettings()) {
    val itemRegistry = MaterialRegistry(this.settings.registerDefaultItems)
    val attributeRegistry = ItemAttributeRegistry(this, this.settings.registerDefaultAttributes)

    fun newKey(key: String) = NamespacedKey(this.plugin, key)
}
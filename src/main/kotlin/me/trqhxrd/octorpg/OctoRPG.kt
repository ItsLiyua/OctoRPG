package me.trqhxrd.octorpg

import me.trqhxrd.octorpg.item.ItemAttributeRegistry
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

class OctoRPG(val plugin: JavaPlugin, val settings: InitSettings = InitSettings()) {
    val attributeRegistry = ItemAttributeRegistry(this, this.settings.registerDefaultAttributes)

    fun newKey(key: String) = NamespacedKey(this.plugin, key)
}
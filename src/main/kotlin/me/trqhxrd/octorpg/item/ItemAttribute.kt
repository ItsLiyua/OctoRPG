package me.trqhxrd.octorpg.item

import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.OctoRPG
import me.trqhxrd.octorpg.api.registry.Registered
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

interface ItemAttribute {

    val octoRPG:OctoRPG
    val id: NamespacedKey

    fun write(nbt: ReadWriteNBT)

    fun read(nbt: ReadWriteNBT)

    fun apply(raw: ItemStack)

    @FunctionalInterface
    interface AttributeBuilder<A : ItemAttribute> : Registered<NamespacedKey> {

        val octoRPG: OctoRPG
        fun build(): A
    }
}
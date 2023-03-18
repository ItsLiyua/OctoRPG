package me.trqhxrd.octorpg.item.attribute

import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.ItemAttribute
import org.bukkit.inventory.ItemStack

class Lore(override val octoRPG: OctoRPG, val lore: MutableList<String> = mutableListOf()) : ItemAttribute {
    override val id = this.octoRPG.newKey("lore")
    override fun write(nbt: ReadWriteNBT) {
        val list = ((nbt.getOrCreateCompound("octo") as ReadWriteNBT)
            .getOrCreateCompound("defaults") as ReadWriteNBT).getStringList("lore")
        list.clear()
        list.addAll(this.lore)
    }

    override fun read(nbt: ReadWriteNBT) {
        this.lore.clear()
        this.lore.addAll(nbt.getCompound("octo").getCompound("defaults").getStringList("lore").toList())
    }

    override fun apply(raw: ItemStack) {
        val meta = raw.itemMeta
        val lore = meta!!.lore ?: mutableListOf()
        lore.add("§c")
        lore.addAll(this.lore)
        meta.lore = lore
        raw.itemMeta = meta
    }

    class Builder(override val octoRPG: OctoRPG) : ItemAttribute.AttributeBuilder<Lore> {
        override val id = this.build().id
        override fun build() = Lore(this.octoRPG)
    }
}
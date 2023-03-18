package me.trqhxrd.octorpg.item.attribute

import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.ItemAttribute
import org.bukkit.inventory.ItemStack

class Lore(override val octoRPG: OctoRPG, val lore: MutableList<String>) : ItemAttribute {
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
        meta!!.lore = this.lore
        raw.itemMeta = meta
    }

    class Builder(override val octoRPG: OctoRPG) : ItemAttribute.AttributeBuilder<Lore> {
        override val id = this.octoRPG.newKey("lore")
        override fun build(): Lore {
            TODO("Not yet implemented")
        }
    }
}
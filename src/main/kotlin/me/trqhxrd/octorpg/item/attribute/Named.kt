package me.trqhxrd.octorpg.item.attribute

import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.ItemAttribute
import org.bukkit.inventory.ItemStack

class Named(override val octoRPG: OctoRPG, var name: String = "Unnamed item") : ItemAttribute {

    override val id = this.octoRPG.newKey("named")

    override fun write(nbt: ReadWriteNBT) {
        ((nbt.getOrCreateCompound("octo") as ReadWriteNBT).getOrCreateCompound("defaults") as ReadWriteNBT)
            .setString("named", this.name)
    }

    override fun read(nbt: ReadWriteNBT) {
        this.name = nbt.getCompound("octo").getCompound("defaults").getString("named")
    }

    override fun apply(raw: ItemStack) {
        val meta = raw.itemMeta!!
        meta.setDisplayName(this.name)
        raw.itemMeta = meta
    }

    class Builder(override val octoRPG: OctoRPG) : ItemAttribute.AttributeBuilder<Named> {
        override val id = this.build().id
        override fun build() = Named(this.octoRPG)
    }
}
package me.trqhxrd.octorpg.item.attribute

import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.OctoRPG
import me.trqhxrd.octorpg.item.ItemAttribute
import org.bukkit.inventory.ItemStack

class Named(override val octoRPG: OctoRPG) : ItemAttribute {

    override val id = this.octoRPG.newKey("named")

    override fun write(nbt: ReadWriteNBT) {
        TODO("Not yet implemented")
    }

    override fun read(nbt: ReadWriteNBT) {
        TODO("Not yet implemented")
    }

    override fun apply(raw: ItemStack) {
        TODO("Not yet implemented")
    }

    class Builder(override val octoRPG: OctoRPG) : ItemAttribute.AttributeBuilder<Named> {

        override val id = this.octoRPG.newKey("named")

        override fun build() = Named(this.octoRPG)
    }
}
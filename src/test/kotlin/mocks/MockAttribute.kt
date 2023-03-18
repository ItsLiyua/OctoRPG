package mocks

import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.ItemAttribute
import org.bukkit.inventory.ItemStack

class MockAttribute(override val octoRPG: OctoRPG) : ItemAttribute {
    override val id = this.octoRPG.newKey("mock")
    override val priority = 0
    override fun write(nbt: ReadWriteNBT) {
    }

    override fun read(nbt: ReadWriteNBT) {
    }

    override fun apply(raw: ItemStack) {
    }
}
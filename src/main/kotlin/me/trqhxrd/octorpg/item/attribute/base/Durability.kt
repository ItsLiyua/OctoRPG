package me.trqhxrd.octorpg.item.attribute.base

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.attribute.ItemAttribute
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

class Durability(
    override val octoRPG: OctoRPG,
    var durability: Int = 100,
    var maxDurability: Int = 100,
    var unbreakable: Boolean = false,
    var formatting: Map<Double, String> = mapOf(0.2 to "§a§l", 0.05 to "§e§l", 0.00 to "§c§l")
) : ItemAttribute {
    override val id = this.octoRPG.newKey("durability")
    override val priority = 0

    override fun write(nbt: ReadWriteNBT) {
        val compound = ((nbt.getOrCreateCompound("octo") as ReadWriteNBT)
            .getOrCreateCompound("defaults") as ReadWriteNBT)
            .getOrCreateCompound("durability") as ReadWriteNBT

        compound.setInteger("current", this.durability)
        compound.setInteger("max", this.maxDurability)
        compound.setBoolean("unbreakable", this.unbreakable)
        compound.setString("format", Gson().toJson(this.formatting))
    }

    override fun read(nbt: ReadWriteNBT) {
        val compound = nbt.getCompound("octo")
            .getCompound("defaults")
            .getCompound("durability")

        this.durability = compound.getInteger("durability")
        this.maxDurability = compound.getInteger("maxDurability")
        this.unbreakable = compound.getBoolean("unbreakable")
        this.formatting = Gson().fromJson(
            compound.getString("format"),
            TypeToken.of(HashMap<Double, String>()::class.java).type
        )
    }

    override fun apply(raw: ItemStack) {
        val meta = raw.itemMeta!!
        val lore = meta.lore ?: mutableListOf()
        lore.add("§c")

        if (this.unbreakable) {
            val color = this.formatting[this.formatting.toSortedMap().lastKey()]!!
            lore.add("${color}UNBREAKABLE")
        } else {

            val left = this.durability.toDouble() / this.maxDurability.toDouble()
            var color = ""
            for (c in this.formatting.toSortedMap()) {
                if (c.key <= left) color = c.value
                else break
            }

            lore.add("$color$durability / $maxDurability Durability")
            if (meta is Damageable) meta.damage = (raw.type.maxDurability - raw.type.maxDurability * left).toInt()
        }

        meta.lore = lore
        raw.itemMeta = meta
    }

    class Builder(override val octoRPG: OctoRPG) : ItemAttribute.AttributeBuilder<Durability> {
        override val id = this.build().id
        override fun build() = Durability(this.octoRPG)
    }
}
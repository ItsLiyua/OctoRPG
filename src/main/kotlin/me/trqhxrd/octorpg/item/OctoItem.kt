package me.trqhxrd.octorpg.item

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.iface.ReadWriteNBT
import me.trqhxrd.octorpg.api.OctoRPG
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack

open class OctoItem(val octoRPG: OctoRPG, val id: NamespacedKey, val type: Material, var amount: Int = 1) {
    private val attributes = mutableSetOf<ItemAttribute>()

    @Suppress("LeakingThis")
    constructor(octoRPG: OctoRPG, raw: ItemStack) : this(octoRPG, OctoItem.extractKey(raw), raw.type, raw.amount) {
        val attrList = NBT.get(raw) { it.getCompound("octo").getStringList("attributes").toList() }
        attrList.map { NamespacedKey.fromString(it)!! }
            .mapNotNull { this.octoRPG.attributeRegistry.instantiateOrNull(it) }
            .onEach { NBT.modify(raw) { nbt -> it.read(nbt) } }
            .forEach(this::addAttribute)
    }

    companion object {
        private fun extractKey(raw: ItemStack): NamespacedKey {
            return NBT.get(raw) {
                val c = it.getCompound("octo")
                if (!c.hasTag("id")) throw NullPointerException("An item you're trying to convert to an octo item does not have an id.")
                val s = c.getString("id")
                return@get NamespacedKey.fromString(s) ?: throw IllegalArgumentException("Could not parse $s to an id.")
            }
        }
    }

    fun attributes() = this.attributes.toSet()

    fun hasAttribute(attribute: ItemAttribute) = this.hasAttribute(attribute.id)

    fun hasAttribute(id: NamespacedKey) = this.attributes.map { it.id }.any { it == id }

    fun addAttribute(attribute: ItemAttribute): Boolean {
        if (this.hasAttribute(attribute)) return false
        this.attributes.add(attribute)
        return true
    }

    fun removeAttribute(key: NamespacedKey) = this.attributes.removeIf { it.id == key }

    fun getAttribute(id: NamespacedKey) =
        this.getAttributeOrNull(id) ?: throw NullPointerException("The item with the ID")

    fun getAttributeOrNull(id: NamespacedKey) = this.attributes.firstOrNull { it.id == id }

    // No unit test because the of NBT modifications taking place when this is called.
    fun build(): ItemStack {
        val item = ItemStack(this.type, this.amount)
        this.attributes.sortedByDescending { it.priority }.forEach { it.apply(item) }
        NBT.modify(item) { this.attributes.forEach { a -> a.write(it) } }
        NBT.modify(item) {
            (it.getOrCreateCompound("octo") as ReadWriteNBT).getStringList("attributes")
                .addAll(this.attributes.map { attr -> attr.id.toString() })
        }
        NBT.modify(item) { (it.getOrCreateCompound("octo") as ReadWriteNBT).setString("id", this.id.toString()) }
        return item
    }

    override fun toString(): String {
        return "OctoItem(octoRPG=$octoRPG, id=$id, type=$type, amount=$amount, attributes=$attributes)"
    }
}
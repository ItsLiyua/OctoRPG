package me.trqhxrd.octorpg.debug

import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.OctoItem
import me.trqhxrd.octorpg.item.attribute.Durability
import me.trqhxrd.octorpg.item.attribute.Lore
import me.trqhxrd.octorpg.item.attribute.Named
import me.trqhxrd.octorpg.item.material.Material

object DiamondOfFriendship : Material("diamond_of_friendship", org.bukkit.Material.DIAMOND) {

    override fun create(octoRPG: OctoRPG): OctoItem {
        val i = OctoItem(octoRPG, this)
        i.addAttribute(Named(octoRPG, "§bDiamond of Friendship"))
        i.addAttribute(Lore(octoRPG, mutableListOf("§7A rare diamond", "§c§lDEBUG ITEM")))
        i.addAttribute(Durability(octoRPG, 200, 200))
        return i
    }
}
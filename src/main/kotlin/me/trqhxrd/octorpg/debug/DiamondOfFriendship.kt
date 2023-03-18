package me.trqhxrd.octorpg.debug

import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.OctoItem
import me.trqhxrd.octorpg.item.attribute.Durability
import me.trqhxrd.octorpg.item.attribute.Lore
import me.trqhxrd.octorpg.item.attribute.Named
import org.bukkit.Material

class DiamondOfFriendship(octoRPG: OctoRPG) :
    OctoItem(octoRPG, octoRPG.newKey("diamond_sword_of_friendship"), Material.DIAMOND) {
    init {
        this.addAttribute(Named(this.octoRPG, "§bDiamond of Friendship"))
        this.addAttribute(Lore(this.octoRPG, mutableListOf("Line1", "§bLine 2")))
        this.addAttribute(Durability(this.octoRPG, 10, 500))
    }
}
package me.trqhxrd.octorpg.debug

import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.OctoItem
import me.trqhxrd.octorpg.item.attribute.Named
import org.bukkit.Material

class DiamondOfFriendship(octoRPG: OctoRPG) :
    OctoItem(octoRPG, octoRPG.newKey("diamond_of_friendship"), Material.DIAMOND) {
    init {
        this.addAttribute(Named(this.octoRPG, name = "§bDiamond of Friendship"))
    }
}
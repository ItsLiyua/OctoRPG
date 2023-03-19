package me.trqhxrd.octorpg.debug

import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.item.OctoItem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(val octoRPG: OctoRPG) : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        e.player.inventory.addItem(DiamondOfFriendship(this.octoRPG).build())
    }
}
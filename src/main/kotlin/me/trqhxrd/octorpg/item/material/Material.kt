package me.trqhxrd.octorpg.item.material

import me.trqhxrd.octorpg.api.OctoRPG
import me.trqhxrd.octorpg.api.registry.Registered
import me.trqhxrd.octorpg.item.OctoItem
import org.bukkit.Material

abstract class Material(override val id: String, val material: Material) : Registered<String> {
    abstract fun create(octoRPG: OctoRPG): OctoItem

    fun build(octoRPG: OctoRPG) = this.create(octoRPG).build()
}
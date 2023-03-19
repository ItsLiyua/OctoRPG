package me.trqhxrd.octorpg.item.material

import me.trqhxrd.octorpg.api.registry.Registry
import me.trqhxrd.octorpg.debug.DiamondOfFriendship

class MaterialRegistry(registerDefaultItems: Boolean = false) : Registry<String, Material>() {

    init {
        if (registerDefaultItems) this.add(DiamondOfFriendship)
    }
}
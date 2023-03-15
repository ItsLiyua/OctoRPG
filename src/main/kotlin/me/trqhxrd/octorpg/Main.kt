package me.trqhxrd.octorpg

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        this.logger.info("Hello World!")
    }
}
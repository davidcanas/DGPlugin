package dev.canas.plugindg

import dev.canas.plugindg.commands.CMDFly
import dev.canas.plugindg.commands.CMDGamemode
import org.bukkit.plugin.java.JavaPlugin

class PluginDG : JavaPlugin() {
    override fun onEnable() {
        println("[PluginDG] Plugin iniciado com sucesso!")
        CMDGamemode.get().register()
        CMDFly.get().register()
    }

    override fun onDisable() {
        println("[PluginDG] Plugin desativado com sucesso!")
    }
}

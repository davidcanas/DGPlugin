package dev.canas.plugindg.commands

import dev.canas.plugindg.lib.sendMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player


object CMDHeal {

    fun get(): CommandAPICommand {
        return CommandAPICommand("heal")
            .withAliases("curar")
            .withArguments(
                PlayerArgument("player").setOptional(true)
            )
            .executesPlayer(PlayerCommandExecutor { player, args ->
                val target = args[0] as? Player ?: player
                target.health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
                player.sendMiniMessage("O jogador <yellow>${target.name}</yellow> foi curado!")


            })
    }

}
package dev.canas.plugindg.commands

import dev.canas.plugindg.lib.sendMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player


object CMDFly {

    fun get(): CommandAPICommand {
        return CommandAPICommand("fly")
            .withAliases("voar")
            .withArguments(
                PlayerArgument("player").setOptional(true)
            )
            .executesPlayer(PlayerCommandExecutor { player, args ->
                val target = args[0] as? Player ?: player
                target.allowFlight = !target.allowFlight
                player.sendMiniMessage("O modo de voo de <yellow>${target.name}</yellow> foi <yellow>${if(target.allowFlight) "ativado" else "desativado"}</yellow>!")

            })
    }

}
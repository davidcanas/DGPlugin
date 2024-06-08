package dev.canas.plugindg.commands

import dev.canas.plugindg.lib.sendMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.entity.Player


object CMDLightning {

    fun get(): CommandAPICommand {
        return CommandAPICommand("lightning")
            .withAliases("smite")
            .withArguments(
                PlayerArgument("player").setOptional(true)
            )
            .executesPlayer(PlayerCommandExecutor { player, args ->
                val target = args[0] as? Player ?: player
                var targetName = target.name
                if (args[0].toString() == "null") {
                    targetName = "no local que você está olhando"
                }
                val targetLocation = (args[0] as? Player)?.location ?: player.getTargetBlock(null, 100).location
                target?.world?.strikeLightning(targetLocation)

                player.sendMiniMessage("Hmmm... Que caiam trovões <yellow>${targetName}</yellow>!")


            })
    }

}
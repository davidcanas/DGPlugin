package dev.canas.plugindg.commands

import dev.canas.plugindg.lib.sendMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player


object CMDGamemode {

    fun get(): CommandAPICommand {
        return CommandAPICommand("gamemode")
            .withAliases("gm")
            .withArguments(
                StringArgument("type"),
                PlayerArgument("player").setOptional(true)
            )
            .executesPlayer(PlayerCommandExecutor { player, args ->
                val type = args[0] as String
                val target = args[1] as? Player ?: player

                val gameMode = when(type) {
                    "0", "s", "survival" -> GameMode.SURVIVAL
                    "1", "c", "creative" -> GameMode.CREATIVE
                    "2", "a", "adventure" -> GameMode.ADVENTURE
                    "3", "sp", "spectator" -> GameMode.SPECTATOR
                    else -> run {
                        "§cModo de jogo inválido! Use /gamemode <0|1|2|3> [player]".also(player::sendMessage)
                        return@PlayerCommandExecutor
                    }
                }

                target.gameMode = gameMode
                player.sendMiniMessage("Modo de jogo de <yellow>${target.name}</yellow> alterado para <yellow>${when(gameMode) {
                    GameMode.SURVIVAL -> "sobrevivência"
                    GameMode.CREATIVE -> "criativo"
                    GameMode.ADVENTURE -> "aventura"
                    GameMode.SPECTATOR -> "espectador"
                    else -> "desconhecido"
                }}</yellow>!")
            })
    }

}
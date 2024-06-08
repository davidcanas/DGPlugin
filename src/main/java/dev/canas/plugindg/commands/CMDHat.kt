package dev.canas.plugindg.commands

import dev.canas.plugindg.lib.sendMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ItemStackArgument
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


object CMDHat {

    fun get(): CommandAPICommand {
        return CommandAPICommand("hat")
            .withPermission("plugindg.hat")
            .withAliases("chapeu")
            .withHelp("Coloca o item na mão como chapéu do jogador", "Coloca o item na mão como chapéu do jogador, se o jogador já tiver um chapéu, ele será trocado com o item na mão")
            .withArguments(
                ItemStackArgument("item").setOptional(true),
                PlayerArgument("player").setOptional(true),
            )
            .executesPlayer(PlayerCommandExecutor { player, args ->
                if (!player.hasPermission("plugindg.hat.custom_item") && args[0] != null) {
                    player.sendMiniMessage("Você não tem permissão para usar itens personalizados!")
                    player.sendMiniMessage("Segure um item do inventário ou peça para um administrador adicionar a permissão <gray>plugindg.hat.custom_item</gray>!")
                    return@PlayerCommandExecutor
                }

                val item = args[0] as? ItemStack ?: player.inventory.itemInMainHand
                val target = args[1] as? Player ?: player
                val helmet = target.inventory.helmet
                target.inventory.helmet = item
                player.inventory.setItemInMainHand(helmet)

                player.sendMiniMessage("O chapéu de <yellow>${target.name}</yellow> foi alterado!")

            })
    }

}
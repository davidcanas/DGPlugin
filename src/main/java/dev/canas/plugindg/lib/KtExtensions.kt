package dev.canas.plugindg.lib

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.entity.Player

fun String.asMini(tagResolver: TagResolver = TagResolver.empty()): Component = MiniMessage.miniMessage().deserialize(this, tagResolver)
fun Player.sendMiniMessage(miniMessage: String, templateResolver: TagResolver = TagResolver.empty()): Unit =
    this.sendMessage(miniMessage.asMini(templateResolver))

package net.cublex.core.listeners

import net.cublex.core.utils.MessageHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val placeholders = mapOf("player" to player.name)

        //MessageHandler.sendMessage(player, "welcome", placeholders)
        MessageHandler.sendTitle(
            player,
            "§6Welcome to Cublex!",      // Title (Başlık)
            "§7Enjoy your stay, ${player.name}!",  // Subtitle (Alt Başlık)
            fadeIn = 20,    // 1 saniye (20 tick)
            stay = 100,     // 5 saniye (100 tick)
            fadeOut = 20    // 1 saniye (20 tick)
        )
    }
}
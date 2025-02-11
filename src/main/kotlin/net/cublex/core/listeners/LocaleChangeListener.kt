package net.cublex.core.listeners

import net.cublex.core.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLocaleChangeEvent

class LocaleChangeListener : Listener {

    @EventHandler
    fun onPlayerLocaleChange(event: PlayerLocaleChangeEvent) {
        val player = event.player
        val newLocale = event.locale  // Oyuncunun yeni dili

        player.sendMessage("🌐 Dil tercihin değişti: $newLocale")

        Main.instance.languageManager.updatePlayerLocale(player, newLocale)
    }
}
package net.cublex.core.utils

import net.cublex.core.Main
import org.bukkit.entity.Player
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import java.time.Duration

object MessageHandler {

    private val languageManager = Main.instance.languageManager

    // Basic
    fun sendMessage(player: Player, path: String) {
        val message = languageManager.getMessage(player, path)
        player.sendMessage(message.replace("&", "§"))
    }

    // Variables
    fun sendMessage(player: Player, path: String, placeholders: Map<String, String>) {
        var message = languageManager.getMessage(player, path)
        placeholders.forEach { (key, value) ->
            message = message.replace("%$key%", value)
        }
        player.sendMessage(message.replace("&", "§"))
    }

    // Title Subtitle
    /*
     - Oyuncuya Title ve Subtitle mesajı gönderir.
     -
     - @param player Mesajın gönderileceği oyuncu.
     - @param title Başlık (Title) metni. (null olabilir)
     - @param subtitle Alt başlık (Subtitle) metni. (null olabilir)
     - @param fadeIn Gösterim süresi (başlangıç). (tick cinsinden)
     - @param stay Mesajın ekranda kalma süresi. (tick cinsinden)
     - @param fadeOut Kapanma süresi. (tick cinsinden)
     */
    fun sendTitle(
        player: Player,
        title: String?,
        subtitle: String?,
        fadeIn: Int = 10,
        stay: Int = 70,
        fadeOut: Int = 20
    ) {
        // Title ve Subtitle mesajlarını oluştur
        val titleComponent = title?.let { Component.text(it).color(NamedTextColor.GOLD) } ?: Component.empty()
        val subtitleComponent = subtitle?.let { Component.text(it).color(NamedTextColor.GRAY) } ?: Component.empty()

        // Süreleri Duration'a çevir
        val titleTimes = Title.Times.times(
            Duration.ofMillis((fadeIn * 50).toLong()),   // 1 tick = 50ms
            Duration.ofMillis((stay * 50).toLong()),
            Duration.ofMillis((fadeOut * 50).toLong())
        )

        // Oyuncuya Title gönder
        player.showTitle(Title.title(titleComponent, subtitleComponent, titleTimes))
    }
}
package net.cublex.core

import net.cublex.core.listeners.JoinListener
import net.cublex.core.listeners.LocaleChangeListener
import net.cublex.core.localization.LanguageManager
import org.bukkit.plugin.java.JavaPlugin
// This is a comment by the author.
class Main : JavaPlugin() {

    companion object {
        lateinit var instance: Main
            private set
    }

    val languageManager: LanguageManager by lazy { LanguageManager(this) }

    override fun onEnable() {
        instance = this

        saveDefaultConfig()
        registerEvents()

        logger.info("✅ CublexCore has been successfully enabled!!!")
    }

    override fun onDisable() {
        logger.info("❌ CublexCore has been disabled.")
    }

    private fun registerEvents() {
        val pluginManager = server.pluginManager
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(LocaleChangeListener(), this)
    }
}
package net.cublex.core.localization

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.cublex.core.Main
import org.bukkit.entity.Player
import java.io.File
import java.io.InputStreamReader

class LanguageManager(private val plugin: Main) {

    private val gson = Gson()
    private val languages = mutableMapOf<String, Map<String, String>>()

    init {
        loadLanguages()
    }

    private fun loadLanguages() {
        val langFolder = File(plugin.dataFolder, "languages")
        if (!langFolder.exists()) {
            langFolder.mkdirs()
        }

        plugin.saveResource("languages/en.json", false)
        plugin.saveResource("languages/tr.json", false)

        langFolder.listFiles { file -> file.name.endsWith(".json") }?.forEach { file ->
            val langCode = file.nameWithoutExtension
            val reader = InputStreamReader(file.inputStream(), Charsets.UTF_8)
            val type = object : TypeToken<Map<String, String>>() {}.type

            val langData: Map<String, String> = gson.fromJson(reader, type)
            languages[langCode] = langData
        }

        if (!languages.containsKey("en")) {
            plugin.logger.warning("Default language file 'en.json' could not be found!")
        }
    }

    fun getMessage(player: Player, path: String): String {
        val locale = player.locale().language
        val langConfig = languages[locale] ?: languages["en"]

        return langConfig?.get(path) ?: getMissingKeyMessage(player, path)
    }

    private fun getMissingKeyMessage(player: Player, missingKey: String): String {
        val locale = player.locale().language
        val langConfig = languages[locale] ?: languages["en"]

        val errorMessage = langConfig?.get("string_key_not_found")
            ?: "§cThe message for '$missingKey' could not be found."

        return errorMessage.replace("%key%", missingKey)
    }

    fun updatePlayerLocale(player: Player, newLocale: String) {
        // Oyuncunun dil ayarını güncelle
        player.sendMessage("🌍 Dilin güncellendi: $newLocale")

        // Burada oyuncunun dilini kaydedebilir veya yeni mesajlar çekebilirsin
    }
}
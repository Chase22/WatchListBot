package io.github.chase22.watchlist

class Config {
    val botUsername = getFromEnv("bot-username", "watch-list.bot")
    val botToken = getFromEnv<String>("bot-token")
    val channelChatId = getFromEnv("channel-id", -1001348399204, String::toLong)
    val archiveChatId = getFromEnv("archive-id", -1001190178078, String::toLong)

    private fun <T> getFromEnv(key: String, default: T? = null, mapper: (String) -> T = {it as T}): T {
        return System.getenv(key)?.let(mapper) ?: default ?: throw IllegalArgumentException("No value for key $key")
    }
}
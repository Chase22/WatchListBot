package io.github.chase22.watchlist

import io.github.chase22.watchlist.bot.WatchListBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    val config = Config()
    val bot = WatchListBot(config, emptyList())

    TelegramBotsApi(DefaultBotSession::class.java).registerBot(bot)

    HealthCheck().start()
}
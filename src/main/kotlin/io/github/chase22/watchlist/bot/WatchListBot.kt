package io.github.chase22.watchlist.bot

import io.github.chase22.watchlist.*
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException

class WatchListBot(private val config: Config, private val commands: List<IBotCommand>) :
    TelegramLongPollingCommandBot() {
    init {
        registerAll(*commands.toTypedArray())
        execute(SetMyCommands(commands.map { BotCommand(it.commandIdentifier, it.description) }))
    }

    override fun getBotToken(): String = config.botToken
    override fun getBotUsername(): String = config.botUsername

    override fun processNonCommandUpdate(update: Update) {
        update.channelPost?.let { message ->
            if (message.chatId == config.channelChatId) {
                message.entities.filter { it.text == "bot_command" }.optionalFirst().ifPresentOrElse({
                    if (it.text.startsWith("/archive") && message.isReply) {
                        archiveMessage(message.replyToMessage)
                    }
                    execute(message.delete())
                }) {
                    execute(
                        EditMessageText.builder()
                            .text(message.text)
                            .replyMarkup(createSingleButtonKeyboard("archive", "archive"))
                            .chatId(message.chatId.toString())
                            .messageId(message.messageId)
                            .build()
                    )
                }
            }
        }
        update.callbackQuery?.let {
            if (it.data == "archive") {
                try {
                    archiveMessage(it.message)
                    execute(it.answer("Successfully archived"))
                } catch (e: TelegramApiException) {
                    if (e is TelegramApiRequestException) {
                        LOGGER.warn("Error forwarding message. ApiResponse: ${e.apiResponse}", e)
                    } else {
                        LOGGER.warn("Error forwarding message", e)
                    }
                }
            }
        }
    }

    private fun archiveMessage(message: Message) {
        execute(message.forwardTo(config.archiveChatId))
        execute(message.delete())
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(WatchListBot::class.java)
    }
}
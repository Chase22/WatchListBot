package io.github.chase22.watchlist

import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message

fun Message.forwardTo(chatId: Long) = ForwardMessage(chatId.toString(), this.chatId.toString(), this.messageId)
fun Message.delete() = DeleteMessage(this.chatId.toString(), this.messageId)
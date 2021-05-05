package io.github.chase22.watchlist

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

fun CallbackQuery.answer(text: String) = AnswerCallbackQuery.builder().text(text).callbackQueryId(this.id).build()
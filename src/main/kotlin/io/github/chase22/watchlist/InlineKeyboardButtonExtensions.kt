package io.github.chase22.watchlist

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

fun createInlineKeyboardButton(text: String, data: String) = InlineKeyboardButton.builder()
    .text(text)
    .callbackData(data)
    .build()

fun createSingleButtonKeyboard(text: String, data: String) = InlineKeyboardMarkup(
    listOf(listOf(createInlineKeyboardButton(text, data)))
)
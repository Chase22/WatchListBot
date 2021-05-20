package io.github.chase22.watchlist

import java.util.*

fun <E> List<E>.optionalFirst() = Optional.ofNullable(this.firstOrNull())
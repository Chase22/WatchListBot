package io.github.chase22.watchlist

import io.undertow.Undertow
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange


class HealthCheck {
    fun start() {
        val server: Undertow = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(HealthHandler()).build()
        server.start()
    }

    internal class HealthHandler : HttpHandler {
        override fun handleRequest(exchange: HttpServerExchange) {
            exchange.statusCode = 200
            exchange.responseSender.close()
        }
    }
}
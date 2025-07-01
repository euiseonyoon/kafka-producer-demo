package com.example.demo.pricesender.binance

import com.example.demo.pricesender.websocket.IWebsocketRequestBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component

@Component
class BinanceWebsocketRequestBuilder : IWebsocketRequestBuilder {
    override val mapper: ObjectMapper = jacksonObjectMapper()
    override fun buildCurrentPriceRequest(uuid: String, tickers: List<String>): String {
        val request = mutableMapOf<String, Any>(
            "id" to uuid,
            "method" to "ticker.price",
        )
        if (tickers.size == 1) {
            request["params"] = mapOf("symbol" to tickers.first())
        }
        val json = jacksonObjectMapper().writeValueAsString(request)
        return json
    }
}

package com.example.demo.pricesender

import com.example.demo.pricesender.binance.BinanceResponseHandler
import com.example.demo.pricesender.binance.BinanceWebsocketRequestBuilder
import com.example.demo.pricesender.websocket.WebsocketRequestSender
import jakarta.annotation.PostConstruct
import okhttp3.OkHttpClient
import org.springframework.stereotype.Component



@Component
class Runner(
    private val okHttpClient: OkHttpClient,
    private val binanceResponseHandler: BinanceResponseHandler,
    private val binanceWebsocketRequestBuilder: BinanceWebsocketRequestBuilder,
) {
    @PostConstruct
    fun init() {
        val url = "wss://ws-fapi.binance.com/ws-fapi/v1"

        val binance = WebsocketRequestSender.create(
            url = url,
            client = okHttpClient,
            responseHandler = binanceResponseHandler,
            websocketRequestBuilder= binanceWebsocketRequestBuilder
        )

        binance.sendPriceRequest(listOf("BTCUSDT"))
    }
}
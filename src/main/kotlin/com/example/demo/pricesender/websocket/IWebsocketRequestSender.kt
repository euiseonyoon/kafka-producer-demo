package com.example.demo.pricesender.websocket

interface IWebsocketRequestSender {
    fun sendPriceRequest(tickers: List<String>)
}

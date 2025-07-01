package com.example.demo.pricesender.websocket

import jakarta.annotation.PreDestroy
import okhttp3.OkHttpClient
import okhttp3.Request

class MyWebsocket(
    private val url: String,
    private val client: OkHttpClient,
    private val responseHandler: (String) -> Unit
) {
    val ws = Request.Builder().url(url).build().let { request ->
        val listener = MyListener(
            client = client,
            request = request,
            onMessageOperation = { message -> responseHandler(message) }
        )
        client.newWebSocket(request, listener)
    }

    @PreDestroy
    fun close() {
        ws.close(1000, "shut down")
    }
}

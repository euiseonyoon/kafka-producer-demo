package com.example.demo.pricesender.websocket

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class MyListener (
    private val client: OkHttpClient,
    private val onMessageOperation: (String) -> Unit,
    private val request: Request,
) : WebSocketListener() {
    private var delay = 1_000L
    private val maxDelay = 60_000L
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onMessage(webSocket: WebSocket, text: String) {
        onMessageOperation(text)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        scope.launch {
            delay(delay)
            delay = (delay * 2).coerceAtMost(maxDelay)
            connect()   // 재연결
        }
    }

    fun connect(): WebSocket =
        client.newWebSocket(request, this)
}

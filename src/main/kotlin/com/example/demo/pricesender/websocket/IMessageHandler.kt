package com.example.demo.pricesender.websocket

import com.example.demo.pricesender.enums.RequestType

interface IMessageHandler {
    fun onMessage(message: String, requestType: RequestType)
}
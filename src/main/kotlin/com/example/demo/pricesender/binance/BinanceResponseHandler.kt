package com.example.demo.pricesender.binance

import com.example.demo.pricesender.enums.RequestType
import com.example.demo.pricesender.websocket.IMessageHandler
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component

@Component
class BinanceResponseHandler : IMessageHandler {
    val mapper = jacksonObjectMapper()

    fun deserializeCurrentPrice(json: String) : BinanceSinglePriceResult{
        return try {
            mapper.readValue(json, BinanceSinglePriceResult::class.java)
        } catch (e: Exception) {
            throw e
        }
    }

    override fun onMessage(message: String, requestType: RequestType) {
//        {
//            "id" : "38e2c995-8c1c-42b6-9460-bf4fc5da70e4",
//            "status" : 200,
//            "result" : {
//                "symbol" : "BTCUSDT",
//                "price" : "106843.10",
//                "time" : 1751441516109
//            },
//            "rateLimits" : [
//                {
//                    "rateLimitType" : "REQUEST_WEIGHT",
//                    "interval" : "MINUTE",
//                    "intervalNum" : 1,
//                    "limit" : 2400,
//                    "count" : 1
//                }
//            ]
//        }
        if (requestType == RequestType.CURRENT_PRICE) {
            deserializeCurrentPrice(message)
        }
    }
}
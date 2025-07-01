package com.example.demo.pricesender.websocket

import com.example.demo.pricesender.enums.RequestType
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import java.util.UUID

class WebsocketRequestSender(
    private val url: String,
    private val okHttpClient: OkHttpClient,
    private val responseHandler: IMessageHandler,
    private val websocketRequestBuilder: IWebsocketRequestBuilder,
): IWebsocketRequestSender {
    var uuidRequestTypeMap = mutableMapOf<String, RequestType>()

    val mapper = jacksonObjectMapper()
    val myWebsocket = MyWebsocket(url, okHttpClient) { res ->
        val uuid = getIdFromResponse(res)
        val requestType = uuidRequestTypeMap[uuid]!!
        responseHandler.onMessage(res, requestType)
    }

    fun getIdFromResponse(res: String): String {
        val rootNode: JsonNode = mapper.readTree(res)
        val id = rootNode["id"]!!.asText()
        return id
    }

    override fun sendPriceRequest(tickers: List<String>) {
        val uuid = UUID.randomUUID().toString()
        val request = websocketRequestBuilder.buildCurrentPriceRequest(uuid, tickers)
        if (myWebsocket.ws.send(request)){
            uuidRequestTypeMap[uuid] = RequestType.CURRENT_PRICE
        }
    }

//    /*override*/fun sendSomeOtherRequest() {
//        val uuid = UUID.randomUUID().toString()
//        val request = websocketRequestBuilder.buildSomeOtherRequest(uuid, "")
//    }

    companion object {
        fun create(
            url: String,
            client: OkHttpClient,
            responseHandler: IMessageHandler,
            websocketRequestBuilder: IWebsocketRequestBuilder,
        ): WebsocketRequestSender {
            return WebsocketRequestSender(
                url = url,
                okHttpClient = client,
                responseHandler = responseHandler,
                websocketRequestBuilder = websocketRequestBuilder
            )
        }
    }
}

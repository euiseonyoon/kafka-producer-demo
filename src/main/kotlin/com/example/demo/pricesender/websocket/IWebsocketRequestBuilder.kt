package com.example.demo.pricesender.websocket

import com.fasterxml.jackson.databind.ObjectMapper

interface IWebsocketRequestBuilder {
    val mapper: ObjectMapper
    // 이건 지금 가격 request 정보조회
    fun buildCurrentPriceRequest(uuid: String, tickers: List<String>): String

    // 이건 예를 들어  지금 order북 정보조회 request 빌드
    // fun buildSomeOtherRequest(uuid: String, tickers: List<Int>): String
}

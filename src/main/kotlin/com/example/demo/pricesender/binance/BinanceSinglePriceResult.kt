package com.example.demo.pricesender.binance

import com.example.demo.pricesender.binance.models.PriceResult
import com.example.demo.pricesender.binance.models.RateLimit

data class BinanceSinglePriceResult(
    val id: String,
    val status: Int,
    val result: PriceResult,
    val rateLimits: List<RateLimit>
)

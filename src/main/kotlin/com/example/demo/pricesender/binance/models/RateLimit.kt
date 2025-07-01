package com.example.demo.pricesender.binance.models

data class RateLimit(
    val rateLimitType: String,
    val interval: String,
    val intervalNum: Int,
    val limit: Int,
    val count: Int
)

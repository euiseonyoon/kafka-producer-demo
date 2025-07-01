package com.example.demo.pricesender.binance.models

data class PriceResult(
    val symbol : String,
    val price: String,
    val time: Long,
)


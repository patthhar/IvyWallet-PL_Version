package com.ivy.core.domain.action.exchange

import com.ivy.data.CurrencyCode
import com.ivy.data.ExchangeRatesMap
import com.ivy.data.exchange.ExchangeProvider
import com.ivy.exchange.RemoteExchangeProvider

class RemoteExchangeProviderFake : RemoteExchangeProvider {
    // usd, inr and eur as base currencies for fake
    private val rates = mapOf(
        "USD" to mapOf(
            "EUR" to 4.0,
            "INR" to 10.0,
            "CAD" to -2.0,
            "AED" to 6.0
        ),
        "INR" to mapOf(
            "EUR" to 5.0,
            "USD" to 1.0,
            "CAD" to -3.0,
            "AED" to 4.0
        ),
        "EUR" to mapOf(
            "USD" to 8.0,
            "INR" to 7.0,
            "CAD" to 4.0,
            "AED" to 9.0
        ),
    )

    override suspend fun fetchExchangeRates(baseCurrency: CurrencyCode): RemoteExchangeProvider.Result {
        return RemoteExchangeProvider.Result(
            ratesMap = rates[baseCurrency] as ExchangeRatesMap,
            provider = ExchangeProvider.Fawazahmed0
        )
    }
}
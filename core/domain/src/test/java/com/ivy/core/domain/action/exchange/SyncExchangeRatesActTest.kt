package com.ivy.core.domain.action.exchange

import com.ivy.core.persistence.dao.exchange.ExchangeRateDao
import com.ivy.exchange.RemoteExchangeProvider
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SyncExchangeRatesActTest {
    private lateinit var syncExchangeRatesAct: SyncExchangeRatesAct
    private lateinit var exchangeProviderFake: RemoteExchangeProvider
    private lateinit var exchangeRateDaoFake: ExchangeRateDao

    @BeforeEach
    fun setUp() {
        exchangeProviderFake = RemoteExchangeProviderFake()
        exchangeRateDaoFake = ExchangeRateDaoFake()
        syncExchangeRatesAct = SyncExchangeRatesAct(
            exchangeProvider = exchangeProviderFake,
            exchangeRateDao = exchangeRateDaoFake
        )
    }

    @Test
    fun `Test SyncCurrencyExchangeRates working correctly`() {

    }

    @AfterEach
    fun tearDown() {

    }
}
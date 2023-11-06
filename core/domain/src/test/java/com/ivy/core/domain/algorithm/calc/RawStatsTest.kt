package com.ivy.core.domain.algorithm.calc

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ivy.core.persistence.algorithm.calc.CalcTrn
import com.ivy.data.transaction.TransactionType
import org.junit.jupiter.api.Test
import java.time.Instant

internal class RawStatsTest {
    private val fiveSecondsAgo = Instant.now().minusSeconds(5)
    private val tenSecondsAgo = Instant.now().minusSeconds(10)
    private val thirtySecondsAgo = Instant.now().minusSeconds(30)

    @Test
    fun `Raw Stats for different CalcTrns`() {
        val stats = rawStats(
            listOf(
                CalcTrn(
                    amount = 50.0,
                    currency = "INR",
                    type = TransactionType.Expense,
                    time = fiveSecondsAgo
                ),
                CalcTrn(
                    amount = 100.0,
                    currency = "USD",
                    type = TransactionType.Expense,
                    time = fiveSecondsAgo
                ),
                CalcTrn(
                    amount = 100.0,
                    currency = "INR",
                    type = TransactionType.Expense,
                    time = tenSecondsAgo
                ),
                CalcTrn(
                    amount = 500.0,
                    currency = "USD",
                    type = TransactionType.Income,
                    time = thirtySecondsAgo
                ),
                CalcTrn(
                    amount = 50.0,
                    currency = "INR",
                    type = TransactionType.Income,
                    time = tenSecondsAgo
                ),
            )
        )

        assertThat(stats.expensesCount).isEqualTo(3)
        assertThat(stats.incomesCount).isEqualTo(2)

        assertThat(stats.newestTrnTime).isEqualTo(fiveSecondsAgo)
        assertThat(stats.expenses).isEqualTo(mapOf("USD" to  100.0))
        assertThat(stats.expenses).isEqualTo(mapOf("INR" to  150.0))

        assertThat(stats.incomes).isEqualTo(mapOf("INR" to 50.0))
        assertThat(stats.incomes).isEqualTo(mapOf("USD" to 500.0))
    }
}
package com.ivy.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ivy.parser.Parser
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ExpressionParserTest {
    private lateinit var parser: Parser<TreeNode>

    @BeforeEach
    fun setUp() {
        parser = expressionParser()
    }

    @ParameterizedTest
    @CsvSource(
        "2*(3+4), 14.0",
        "3+(4*2)-1, 10.0",
        "(5-2)*(6/2), 9.0",
        "(10-3)^2/7, 7.0",
        "(2^3-1)/7, 1.0",
        "2*(3+(4/2))-2, 8.0",
        "(((2*3)^2)/2)-5, 13.0",
        "(8+4)/(2*(6-3)), 2.0",
        "(10+2)/(3*2)-1, 1.0",
        "5.0000, 5.0",
        "((7*3)-((5/2)*4)+1)/3, 4.0",
    )
    fun `Test evaluating expression working`(expression: String, expected: Double) {
        val result = parser(expression).first()
        val actual = result.value.eval()
        assertThat(actual).isEqualTo(expected)
    }
}

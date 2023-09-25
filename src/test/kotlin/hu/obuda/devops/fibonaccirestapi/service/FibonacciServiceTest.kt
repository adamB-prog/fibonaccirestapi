package hu.obuda.devops.fibonaccirestapi.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibonacciServiceTest {

    private val underTest = FibonacciService()
    @Test
    fun shouldReturn1WhenCall1() {
        // given

        // when
        val result: Int = underTest.fibonacci(1)
        // then
        Assertions.assertEquals(1, result)
    }
    @ParameterizedTest
    @CsvSource("10, 55",
            "1, 1",
            "2, 1",
            "4, 3",
            "46, 1836311903"
    )
    fun testWithValues(value: Int, expected: Int)
    {
        //given
        val input = value;
        //when
        val result = underTest.fibonacci(input);
        //then
        Assertions.assertEquals(expected, result);

    }
    // TODO - Test with greater numbers and test edge cases
}
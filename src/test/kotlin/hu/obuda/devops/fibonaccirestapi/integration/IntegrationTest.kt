package hu.obuda.devops.fibonaccirestapi.integration

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTest {

    var restTemplate = RestTemplate()

    @ParameterizedTest
    @CsvSource(
            "10, 55",
            "1, 1",
            "2, 1",
            "4, 3",
            "46, 1836311903"
    )
    fun callFibonacciEndpoint(value: Int, expected: Int) {
        // given

        // when
        val entity = restTemplate.getForEntity(
                "http://localhost:8080/fibonacci?n=$value",
            String::class.java
        )

        // then
        Assertions.assertEquals(HttpStatus.OK, entity.statusCode)
        Assertions.assertEquals(expected.toString(), entity.body)
    }

    @Test
    @Throws(Exception::class)
    fun callFibonacciEndpointWithInvalid() {
        // given

        // when
        val thrown = Assertions.assertThrows(
            RestClientException::class.java
        ) {
            restTemplate.getForEntity(
                "http://localhost:8080/fibonacci?n=47",
                String::class.java
            )
        }

        // then
        Assertions.assertNotNull(thrown)
    }

}
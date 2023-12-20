package hello

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests(@Autowired private val restTemplate: TestRestTemplate) {

	@Test
	fun findAllCustomers() {
		val expectedContent = "[{\"firstName\":\"Jack\",\"lastName\":\"Bauer\",\"id\":1},{\"firstName\":\"Chloe\",\"lastName\":\"O'Brian\",\"id\":2},{\"firstName\":\"Kim\",\"lastName\":\"Bauer\",\"id\":3},{\"firstName\":\"David\",\"lastName\":\"Palmer\",\"id\":4},{\"firstName\":\"Michelle\",\"lastName\":\"Dessler\",\"id\":5},{\"firstName\":\"Tony\",\"lastName\":\"Stark\",\"id\":6}]"
		val actualContent = restTemplate.getForObject("/customers", String::class.java)
		assertEquals(expectedContent, actualContent)
	}

	// New test case for creating a customer
	@Test
	fun createNewCustomer() {
		val newCustomer = Customer("Tony", "Stark")
		val expectedContent = """{"firstName":"Tony","lastName":"Stark","id":6}"""

		val response = restTemplate.postForEntity("/customers", newCustomer, String::class.java)
		assertEquals(HttpStatus.CREATED, response.statusCode)

		val actualContent = response.body
		assertEquals(expectedContent, actualContent)
	}
}

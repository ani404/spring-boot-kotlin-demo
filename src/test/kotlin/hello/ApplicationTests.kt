package hello

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests(@Autowired private val restTemplate: TestRestTemplate) {

	@Test
	fun findAllCustomers() {
		val expectedContent = """[{"firstName":"Jack","lastName":"Bauer","id":1},{"firstName":"Chloe","lastName":"O'Brian","id":2},{"firstName":"Kim","lastName":"Bauer","id":3},{"firstName":"David","lastName":"Palmer","id":4},{"firstName":"Michelle","lastName":"Dessler","id":5}]"""
		val actualContent = restTemplate.getForObject("/customers",String::class.java)
		assertEquals(expectedContent, actualContent)
	}

//	@Test
//	fun findCustomerById() {
//		val expectedContent = """{"firstName":"Jack","lastName":"Bauer","id":1}"""
//		val actualContent = restTemplate.getForObject("/customers/1", String::class.java)
//		assertEquals(expectedContent, actualContent)
//	}
//
//	@Test
//	fun findCustomersByLastName() {
//		val expectedContent = """[{"firstName":"Jack","lastName":"Bauer","id":1},{"firstName":"Kim","lastName":"Bauer","id":3}]"""
//		val actualContent = restTemplate.getForObject("/customers/lastname/Bauer", String::class.java)
//		assertEquals(expectedContent, actualContent)
//	}
//
//	@Test
//	fun createNewCustomer() {
//		val customerToCreate = Customer("Tony", "Stark")
//		val expectedContent = """{"firstName":"Tony","lastName":"Stark","id":6}"""
//
//		val response = restTemplate.postForEntity("/customers", customerToCreate, String::class.java)
//		assertEquals(HttpStatus.CREATED, response.statusCode)
//
//		val actualContent = response.body
//		assertEquals(expectedContent, actualContent)
//	}
//
//	@Test
//	fun updateCustomer() {
//		val updatedCustomer = Customer("Updated", "Name")
//		val expectedContent = """{"firstName":"Updated","lastName":"Name","id":2}"""
//
//		val entity = HttpEntity(updatedCustomer)
//		val response = restTemplate.exchange<String>("/customers/2", HttpMethod.PUT, entity)
//		assertEquals(HttpStatus.OK, response.statusCode)
//
//		val actualContent = restTemplate.getForObject("/customers/2", String::class.java)
//		assertEquals(expectedContent, actualContent)
//	}
//
//	@Test
//	fun deleteCustomer() {
//		restTemplate.delete("/customers/5")
//		val deletedCustomer = restTemplate.getForObject("/customers/5", String::class.java)
//		assertEquals(null, deletedCustomer)
//	}
}

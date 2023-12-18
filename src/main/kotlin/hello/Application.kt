package hello

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class Application {

	private val log = LoggerFactory.getLogger(Application::class.java)

	@Bean
	fun initData(repository: CustomerRepository) = CommandLineRunner {
		val customers = listOf(
			Customer("Jack", "Bauer"),
			Customer("Chloe", "O'Brian"),
			Customer("Kim", "Bauer"),
			Customer("David", "Palmer"),
			Customer("Michelle", "Dessler")
		)

		repository.saveAll(customers)

		logCustomers("findAll", repository.findAll())
		logCustomerById(repository.findById(1L))
		logCustomers("findByLastName('Bauer')", repository.findByLastName("Bauer"))
	}

	private fun logCustomers(operation: String, customers: Iterable<Customer>) {
		log.info("Customers found with $operation:")
		log.info("-------------------------------")
		customers.forEach { log.info(it.toString()) }
		log.info("")
	}

	private fun logCustomerById(customer: Optional<Customer>) {
		customer.ifPresent {
			log.info("Customer found with findById(1L):")
			log.info("--------------------------------")
			log.info(it.toString())
			log.info("")
		}
	}
}

fun main() {
	runApplication<Application>()
}

package hello

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(private val repository: CustomerRepository) {

	@GetMapping
	fun findAll(): MutableIterable<Customer> = repository.findAll()

	@GetMapping("/{lastName}")
	fun findByLastName(@PathVariable lastName: String) = repository.findByLastName(lastName)

	@PostMapping
	fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> {
		val createdCustomer = repository.save(customer)
		return ResponseEntity(createdCustomer, HttpStatus.CREATED)
	}

	@PutMapping("/{id}")
	fun updateCustomer(@PathVariable id: Long, @RequestBody updatedCustomer: Customer): ResponseEntity<Customer> {
		if (repository.existsById(id)) {
			updatedCustomer.id = id
			val updated = repository.save(updatedCustomer)
			return ResponseEntity(updated, HttpStatus.OK)
		}
		return ResponseEntity(HttpStatus.NOT_FOUND)
	}

	@DeleteMapping("/{id}")
	fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Void> {
		return if (repository.existsById(id)) {
			repository.deleteById(id)
			ResponseEntity(HttpStatus.NO_CONTENT)
		} else {
			ResponseEntity(HttpStatus.NOT_FOUND)
		}
	}
}

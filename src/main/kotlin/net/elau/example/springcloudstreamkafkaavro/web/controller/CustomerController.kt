package net.elau.example.springcloudstreamkafkaavro.web.controller

import net.elau.example.springcloudstreamkafkaavro.mapper.CustomerMapper
import net.elau.example.springcloudstreamkafkaavro.service.CustomerService
import net.elau.example.springcloudstreamkafkaavro.web.request.CreateCustomerRequest
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
        private val customerMapper: CustomerMapper,
        private val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody createCustomerRequest: CreateCustomerRequest) {
        val createCustomerDto = customerMapper.toDto(createCustomerRequest)
        customerService.create(createCustomerDto)
    }
}

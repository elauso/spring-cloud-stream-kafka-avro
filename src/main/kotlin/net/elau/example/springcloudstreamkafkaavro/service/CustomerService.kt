package net.elau.example.springcloudstreamkafkaavro.service

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.event.stream.producer.CreateCustomerProducer
import net.elau.example.springcloudstreamkafkaavro.exception.EntityAlreadyExistsException
import net.elau.example.springcloudstreamkafkaavro.mapper.CustomerMapper
import net.elau.example.springcloudstreamkafkaavro.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(
        private val customerMapper: CustomerMapper,
        private val customerRepository: CustomerRepository,
        private val createCustomerProducer: CreateCustomerProducer
) {

    @Transactional(propagation = NOT_SUPPORTED)
    fun findByDocument(document: String) =
        customerRepository.findByDocument(document).map {
            customerMapper.toDto(it)
        }

    @Transactional
    fun register(createCustomerDto: CreateCustomerDto) {
        val searchCustomerDtos = findByDocument(createCustomerDto.document)
        if (searchCustomerDtos.isNotEmpty()) {
            throw EntityAlreadyExistsException("Customer received [$createCustomerDto] already exists.")
        }
        val customer = customerMapper.toModel(createCustomerDto)
        customerRepository.save(customer)
    }

    fun apply(createCustomerDto: CreateCustomerDto) {
        createCustomerProducer.produce(createCustomerDto)
    }
}

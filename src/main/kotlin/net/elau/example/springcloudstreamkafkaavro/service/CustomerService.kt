package net.elau.example.springcloudstreamkafkaavro.service

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.event.stream.producer.CreateCustomerProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(private val createCustomerProducer: CreateCustomerProducer) {

    @Transactional
    fun create(createCustomerDto: CreateCustomerDto) {
        this.createCustomerProducer.runCatching {
            this.produce(createCustomerDto)
        }.onFailure {
            throw Exception("Failed to produce the request for create customer [$createCustomerDto]: ${it.message}", it)
        }
    }
}

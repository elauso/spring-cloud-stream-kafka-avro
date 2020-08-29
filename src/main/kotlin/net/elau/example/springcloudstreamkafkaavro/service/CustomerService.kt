package net.elau.example.springcloudstreamkafkaavro.service

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.event.stream.producer.CreateCustomerProducer
import org.springframework.stereotype.Service

@Service
class CustomerService(private val createCustomerProducer: CreateCustomerProducer) {

    fun create(createCustomerDto: CreateCustomerDto) {
        createCustomerProducer.produce(createCustomerDto)
    }
}

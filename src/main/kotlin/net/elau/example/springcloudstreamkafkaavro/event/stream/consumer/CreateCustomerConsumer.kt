package net.elau.example.springcloudstreamkafkaavro.event.stream.consumer

import net.elau.example.springcloudstreamkafkaavro.exception.EntityAlreadyExistsException
import net.elau.example.springcloudstreamkafkaavro.exception.EventConsumerException
import net.elau.example.springcloudstreamkafkaavro.mapper.CustomerMapper
import net.elau.example.springcloudstreamkafkaavro.schema.CreateCustomerEvent
import net.elau.example.springcloudstreamkafkaavro.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink.INPUT
import org.springframework.stereotype.Component

@Component
class CreateCustomerConsumer(
        private val customerMapper: CustomerMapper,
        private val customerService: CustomerService
) {
    companion object {
        private val log = LoggerFactory.getLogger(CreateCustomerConsumer::class.java)
    }

    @StreamListener(INPUT)
    fun consume(createCustomerEvent: CreateCustomerEvent) {
        log.debug("Consuming event message [$createCustomerEvent]")
        customerService.runCatching {
            val createCustomerDto = customerMapper.toDto(createCustomerEvent)
            register(createCustomerDto)
            log.debug("Event [$createCustomerEvent] consumed with success.")
        }.onFailure {
            when (it) {
                is EntityAlreadyExistsException -> log.warn("Request customer [$createCustomerEvent] already registered.")
                else -> throw EventConsumerException("Failed to consume event [$createCustomerEvent]", it)
            }
        }
    }
}

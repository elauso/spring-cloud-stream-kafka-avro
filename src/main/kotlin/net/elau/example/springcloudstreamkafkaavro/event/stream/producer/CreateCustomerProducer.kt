package net.elau.example.springcloudstreamkafkaavro.event.stream.producer

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.event.stream.StreamBinding
import net.elau.example.springcloudstreamkafkaavro.exception.EventProducerException
import net.elau.example.springcloudstreamkafkaavro.mapper.CustomerMapper
import org.slf4j.LoggerFactory
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class CreateCustomerProducer(
        private val streamBinding: StreamBinding,
        private val customerMapper: CustomerMapper
) {
    companion object {
        private val log = LoggerFactory.getLogger(CreateCustomerProducer::class.java)
    }

    fun produce(createCustomerDto: CreateCustomerDto) {
        val createCustomerEvent = customerMapper.toEvent(createCustomerDto)
        streamBinding.runCatching {
            val message = MessageBuilder.withPayload(createCustomerEvent).build()
            publisher().send(message)
            log.debug("Event message [$message] sended with success.")
        }.onFailure {
            throw EventProducerException("Failed to produce event [$createCustomerEvent]", it)
        }
    }
}

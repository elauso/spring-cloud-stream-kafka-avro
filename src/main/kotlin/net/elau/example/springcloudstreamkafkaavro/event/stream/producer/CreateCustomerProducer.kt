package net.elau.example.springcloudstreamkafkaavro.event.stream.producer

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.mapper.CustomerMapper
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class CreateCustomerProducer(
        private val processor: Processor,
        private val customerMapper: CustomerMapper
) {
    fun produce(createCustomerDto: CreateCustomerDto) {
        val createCustomerEvent = customerMapper.toEvent(createCustomerDto)
        processor.output().send(MessageBuilder.withPayload(createCustomerEvent).build())
    }
}

package net.elau.example.springcloudstreamkafkaavro.event.stream.producer

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.mapper.CustomerMapper
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class CreateCustomerProducer(
        private val processor: Processor,
        private val customerMapper: CustomerMapper
) {
    companion object {
        private val log = LoggerFactory.getLogger(CreateCustomerProducer::class.java)
    }

    fun produce(createCustomerDto: CreateCustomerDto) {
        this.processor.runCatching {
            val message = with(createCustomerDto) {
                val event = this@CreateCustomerProducer.customerMapper.toEvent(this)
                MessageBuilder.withPayload(event).build()
            }
            this.output().send(message)
            log.debug("Message sent with success: [$message]")
        }.onFailure {
            throw Exception("Failed to publish the message [$createCustomerDto]: ${it.message}", it)
        }
    }
}

package net.elau.example.springcloudstreamkafkaavro.event.stream.consumer

import net.elau.example.springcloudstreamkafkaavro.schema.CreateCustomerEvent
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink.INPUT
import org.springframework.stereotype.Component

@Component
class CreateCustomerConsumer {
    companion object {
        private val log = LoggerFactory.getLogger(CreateCustomerConsumer::class.java)
    }

    @StreamListener(INPUT)
    fun consume(createCustomerEvent: CreateCustomerEvent) {
        log.debug("Received message: $createCustomerEvent")
    }
}

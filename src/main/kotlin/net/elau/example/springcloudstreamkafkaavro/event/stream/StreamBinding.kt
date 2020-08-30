package net.elau.example.springcloudstreamkafkaavro.event.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.messaging.Sink.INPUT
import org.springframework.cloud.stream.messaging.Source.OUTPUT
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface StreamBinding {

    @Output(OUTPUT)
    fun publisher(): MessageChannel

    @Input(INPUT)
    fun subscriber(): SubscribableChannel
}

package net.elau.example.springcloudstreamkafkaavro

import net.elau.example.springcloudstreamkafkaavro.event.stream.StreamBinding
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@SpringBootApplication
@EnableBinding(StreamBinding::class)
class SpringCloudStreamKafkaAvroApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudStreamKafkaAvroApplication>(*args)
}

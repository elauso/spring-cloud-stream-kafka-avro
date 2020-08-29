package net.elau.example.springcloudstreamkafkaavro.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableSchemaRegistryClient
class SchemaRegistryClientConfig {

    @Value("\${spring.cloud.stream.kafka.binder.producer-properties.schema.registry.url}")
    private val endPoint: String? = null

    @Bean
    fun schemaRegistryClient(): SchemaRegistryClient? {
        val client = ConfluentSchemaRegistryClient()
        client.setEndpoint(endPoint)
        return client
    }
}
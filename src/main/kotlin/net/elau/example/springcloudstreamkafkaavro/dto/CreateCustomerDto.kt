package net.elau.example.springcloudstreamkafkaavro.dto

data class CreateCustomerDto(

        val firstName: String,

        val lastName: String,

        val document: String,

        val email: String
)
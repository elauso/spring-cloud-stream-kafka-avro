package net.elau.example.springcloudstreamkafkaavro.web.request

data class CreateCustomerRequest(

        val firstName: String,

        val lastName: String,

        val document: String,

        val email: String
)
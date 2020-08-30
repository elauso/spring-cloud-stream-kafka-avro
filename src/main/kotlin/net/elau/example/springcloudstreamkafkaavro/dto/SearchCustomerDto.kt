package net.elau.example.springcloudstreamkafkaavro.dto

data class SearchCustomerDto(

        val id: Long,

        val firstName: String,

        val lastName: String,

        val document: String,

        val email: String
)
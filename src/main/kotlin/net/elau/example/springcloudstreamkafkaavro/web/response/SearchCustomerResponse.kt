package net.elau.example.springcloudstreamkafkaavro.web.response

data class SearchCustomerResponse(

        val id: Long,

        val firstName: String,

        val lastName: String,

        val document: String,

        val email: String
)
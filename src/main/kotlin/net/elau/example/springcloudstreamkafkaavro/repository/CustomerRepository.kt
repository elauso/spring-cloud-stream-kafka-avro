package net.elau.example.springcloudstreamkafkaavro.repository

import net.elau.example.springcloudstreamkafkaavro.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {

    fun findByDocument(document: String): List<Customer>
}
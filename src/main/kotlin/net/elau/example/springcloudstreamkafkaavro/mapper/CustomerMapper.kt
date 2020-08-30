package net.elau.example.springcloudstreamkafkaavro.mapper

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.dto.SearchCustomerDto
import net.elau.example.springcloudstreamkafkaavro.model.Customer
import net.elau.example.springcloudstreamkafkaavro.schema.CreateCustomerEvent
import net.elau.example.springcloudstreamkafkaavro.web.request.CreateCustomerRequest
import net.elau.example.springcloudstreamkafkaavro.web.response.SearchCustomerResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CustomerMapper {

    fun toResponse(searchCustomerDtos: List<SearchCustomerDto>): List<SearchCustomerResponse>

    fun toDto(createCustomerEvent: CreateCustomerEvent): CreateCustomerDto

    fun toDto(createCustomerRequest: CreateCustomerRequest): CreateCustomerDto

    fun toDto(customers: List<Customer>): List<SearchCustomerDto>

    fun toEvent(createCustomerDto: CreateCustomerDto): CreateCustomerEvent

    fun toModel(createCustomerDto: CreateCustomerDto): Customer
}
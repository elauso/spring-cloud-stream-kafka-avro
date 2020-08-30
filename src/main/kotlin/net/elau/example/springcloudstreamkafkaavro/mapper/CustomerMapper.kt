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

    fun toResponse(searchCustomerDto: SearchCustomerDto): SearchCustomerResponse

    fun toDto(createCustomerEvent: CreateCustomerEvent): CreateCustomerDto

    fun toDto(createCustomerRequest: CreateCustomerRequest): CreateCustomerDto

    fun toDto(customer: Customer): SearchCustomerDto

    fun toEvent(createCustomerDto: CreateCustomerDto): CreateCustomerEvent

    fun toModel(createCustomerDto: CreateCustomerDto): Customer
}
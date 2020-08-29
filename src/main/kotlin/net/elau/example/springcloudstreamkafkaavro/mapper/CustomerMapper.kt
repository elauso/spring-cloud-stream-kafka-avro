package net.elau.example.springcloudstreamkafkaavro.mapper

import net.elau.example.springcloudstreamkafkaavro.dto.CreateCustomerDto
import net.elau.example.springcloudstreamkafkaavro.schema.CreateCustomer
import net.elau.example.springcloudstreamkafkaavro.web.request.CreateCustomerRequest
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CustomerMapper {

    fun toDto(createCustomerRequest: CreateCustomerRequest): CreateCustomerDto

    fun toEvent(createCustomerDto: CreateCustomerDto): CreateCustomer
}
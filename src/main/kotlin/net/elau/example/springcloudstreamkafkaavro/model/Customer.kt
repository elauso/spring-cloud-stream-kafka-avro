package net.elau.example.springcloudstreamkafkaavro.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id

@Entity
data class Customer(

        @Id
        @GeneratedValue(strategy = AUTO)
        var id: Long? = null,

        @Column(nullable = false)
        var firstName: String? = null,

        @Column(nullable = false)
        var lastName: String? = null,

        @Column(nullable = false, unique = true)
        var document: String? = null,

        @Column(nullable = false)
        var email: String? = null
)

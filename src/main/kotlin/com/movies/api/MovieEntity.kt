package com.movies.api

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Column
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity

@Entity
@Table(name = "movies")
open class MovieEntity : PanacheEntity() {
    @Column(nullable = false)
    open var title: String? = null

    @Column
    open var director: String? = null

    @Column
    open var year: Int? = null

    @Column
    open var genre: String? = null
} 
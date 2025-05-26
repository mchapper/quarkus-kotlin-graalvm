package com.movies.api

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class MovieRepository : PanacheRepository<MovieEntity> 
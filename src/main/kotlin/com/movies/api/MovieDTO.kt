package com.movies.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class MovieDTO(
    val id: Long? = null,
    val title: String,
    val director: String? = null,
    val year: Int? = null,
    val genre: String? = null
) {
    @JsonCreator
    constructor() : this(null, "", null, null, null)
} 
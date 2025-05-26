package com.movies.api

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.Response

@ApplicationScoped
class MovieService(private val repository: MovieRepository) {

    fun getAllMovies(): List<MovieDTO> {
        return repository.listAll().map { it.toDTO() }
    }

    fun getMovieById(id: Long): MovieDTO {
        return repository.findById(id)?.toDTO()
            ?: throw WebApplicationException("Movie with id $id not found", Response.Status.NOT_FOUND)
    }

    @Transactional
    fun createMovie(movieDTO: MovieDTO): MovieDTO {
        val movie = MovieEntity().apply {
            title = movieDTO.title
            director = movieDTO.director
            year = movieDTO.year
            genre = movieDTO.genre
        }
        repository.persist(movie)
        return movie.toDTO()
    }

    @Transactional
    fun updateMovie(id: Long, movieDTO: MovieDTO): MovieDTO {
        val movie = repository.findById(id)
            ?: throw WebApplicationException("Movie with id $id not found", Response.Status.NOT_FOUND)
        
        movie.apply {
            title = movieDTO.title
            director = movieDTO.director
            year = movieDTO.year
            genre = movieDTO.genre
        }
        
        return movie.toDTO()
    }

    @Transactional
    fun deleteMovie(id: Long) {
        if (!repository.deleteById(id)) {
            throw WebApplicationException("Movie with id $id not found", Response.Status.NOT_FOUND)
        }
    }

    private fun MovieEntity.toDTO() = MovieDTO(
        id = id,
        title = title ?: "",
        director = director,
        year = year,
        genre = genre
    )
} 
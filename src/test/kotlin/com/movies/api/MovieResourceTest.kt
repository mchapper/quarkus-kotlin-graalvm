package com.movies.api

import io.mockk.*
import jakarta.ws.rs.core.Response
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MovieResourceTest {

    private lateinit var service: MovieService
    private lateinit var resource: MovieResource

    @BeforeEach
    fun setup() {
        service = mockk()
        resource = MovieResource(service)
    }

    @Test
    fun `getAllMovies should return all movies from service`() {
        // Given
        val movies = listOf(
            MovieDTO(
                id = 1L,
                title = "Inception",
                director = "Christopher Nolan",
                year = 2010,
                genre = "Sci-Fi"
            ),
            MovieDTO(
                id = 2L,
                title = "The Matrix",
                director = "Lana Wachowski",
                year = 1999,
                genre = "Sci-Fi"
            )
        )
        every { service.getAllMovies() } returns movies

        // When
        val result = resource.getAllMovies()

        // Then
        assertEquals(2, result.size)
        assertEquals("Inception", result[0].title)
        assertEquals("The Matrix", result[1].title)
        verify { service.getAllMovies() }
    }

    @Test
    fun `getMovieById should return movie from service`() {
        // Given
        val movie = MovieDTO(
            id = 1L,
            title = "Inception",
            director = "Christopher Nolan",
            year = 2010,
            genre = "Sci-Fi"
        )
        every { service.getMovieById(1L) } returns movie

        // When
        val result = resource.getMovieById(1L)

        // Then
        assertEquals("Inception", result.title)
        assertEquals("Christopher Nolan", result.director)
        verify { service.getMovieById(1L) }
    }

    @Test
    fun `createMovie should return created response with movie`() {
        // Given
        val movieDTO = MovieDTO(
            title = "Inception",
            director = "Christopher Nolan",
            year = 2010,
            genre = "Sci-Fi"
        )
        val createdMovie = movieDTO.copy(id = 1L)
        every { service.createMovie(movieDTO) } returns createdMovie

        // When
        val response = resource.createMovie(movieDTO)

        // Then
        assertEquals(Response.Status.CREATED.statusCode, response.status)
        assertEquals(createdMovie, response.entity)
        verify { service.createMovie(movieDTO) }
    }

    @Test
    fun `updateMovie should return updated movie from service`() {
        // Given
        val movieDTO = MovieDTO(
            title = "New Title",
            director = "New Director",
            year = 2020,
            genre = "New Genre"
        )
        val updatedMovie = movieDTO.copy(id = 1L)
        every { service.updateMovie(1L, movieDTO) } returns updatedMovie

        // When
        val result = resource.updateMovie(1L, movieDTO)

        // Then
        assertEquals("New Title", result.title)
        assertEquals("New Director", result.director)
        verify { service.updateMovie(1L, movieDTO) }
    }

    @Test
    fun `deleteMovie should return no content response`() {
        // Given
        every { service.deleteMovie(1L) } just Runs

        // When
        val response = resource.deleteMovie(1L)

        // Then
        assertEquals(Response.Status.NO_CONTENT.statusCode, response.status)
        verify { service.deleteMovie(1L) }
    }
} 
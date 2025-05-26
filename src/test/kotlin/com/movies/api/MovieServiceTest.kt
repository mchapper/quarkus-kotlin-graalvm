package com.movies.api

import io.mockk.*
import jakarta.ws.rs.WebApplicationException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MovieServiceTest {

    private lateinit var repository: MovieRepository
    private lateinit var service: MovieService

    @BeforeEach
    fun setup() {
        repository = mockk()
        service = MovieService(repository)
    }

    @Test
    fun `getAllMovies should return all movies`() {
        // Given
        val movies = listOf(
            MovieEntity().apply {
                id = 1L
                title = "Inception"
                director = "Christopher Nolan"
                year = 2010
                genre = "Sci-Fi"
            },
            MovieEntity().apply {
                id = 2L
                title = "The Matrix"
                director = "Lana Wachowski"
                year = 1999
                genre = "Sci-Fi"
            }
        )
        every { repository.listAll() } returns movies

        // When
        val result = service.getAllMovies()

        // Then
        assertEquals(2, result.size)
        assertEquals("Inception", result[0].title)
        assertEquals("The Matrix", result[1].title)
        verify { repository.listAll() }
    }

    @Test
    fun `getMovieById should return movie when it exists`() {
        // Given
        val movie = MovieEntity().apply {
            id = 1L
            title = "Inception"
            director = "Christopher Nolan"
            year = 2010
            genre = "Sci-Fi"
        }
        every { repository.findById(1L) } returns movie

        // When
        val result = service.getMovieById(1L)

        // Then
        assertEquals("Inception", result.title)
        assertEquals("Christopher Nolan", result.director)
        verify { repository.findById(1L) }
    }

    @Test
    fun `getMovieById should throw exception when movie not found`() {
        // Given
        every { repository.findById(999L) } returns null

        // When/Then
        assertThrows<WebApplicationException> {
            service.getMovieById(999L)
        }
        verify { repository.findById(999L) }
    }

    @Test
    fun `createMovie should persist and return new movie`() {
        // Given
        val movieDTO = MovieDTO(
            title = "Inception",
            director = "Christopher Nolan",
            year = 2010,
            genre = "Sci-Fi"
        )
        every { repository.persist(any<MovieEntity>()) } just Runs

        // When
        val result = service.createMovie(movieDTO)

        // Then
        assertEquals("Inception", result.title)
        assertEquals("Christopher Nolan", result.director)
        verify { repository.persist(any<MovieEntity>()) }
    }

    @Test
    fun `updateMovie should update and return movie when it exists`() {
        // Given
        val existingMovie = MovieEntity().apply {
            id = 1L
            title = "Old Title"
            director = "Old Director"
            year = 2000
            genre = "Old Genre"
        }
        val movieDTO = MovieDTO(
            title = "New Title",
            director = "New Director",
            year = 2020,
            genre = "New Genre"
        )
        every { repository.findById(1L) } returns existingMovie

        // When
        val result = service.updateMovie(1L, movieDTO)

        // Then
        assertEquals("New Title", result.title)
        assertEquals("New Director", result.director)
        assertEquals(2020, result.year)
        assertEquals("New Genre", result.genre)
        verify { repository.findById(1L) }
    }

    @Test
    fun `updateMovie should throw exception when movie not found`() {
        // Given
        val movieDTO = MovieDTO(
            title = "New Title",
            director = "New Director",
            year = 2020,
            genre = "New Genre"
        )
        every { repository.findById(999L) } returns null

        // When/Then
        assertThrows<WebApplicationException> {
            service.updateMovie(999L, movieDTO)
        }
        verify { repository.findById(999L) }
    }

    @Test
    fun `deleteMovie should delete movie when it exists`() {
        // Given
        every { repository.deleteById(1L) } returns true

        // When
        service.deleteMovie(1L)

        // Then
        verify { repository.deleteById(1L) }
    }

    @Test
    fun `deleteMovie should throw exception when movie not found`() {
        // Given
        every { repository.deleteById(999L) } returns false

        // When/Then
        assertThrows<WebApplicationException> {
            service.deleteMovie(999L)
        }
        verify { repository.deleteById(999L) }
    }
} 
package com.movies.api

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/api/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieResource(private val service: MovieService) {

    @GET
    fun getAllMovies(): List<MovieDTO> = service.getAllMovies()

    @GET
    @Path("/{id}")
    fun getMovieById(@PathParam("id") id: Long): MovieDTO = service.getMovieById(id)

    @POST
    fun createMovie(movieDTO: MovieDTO): Response {
        val created = service.createMovie(movieDTO)
        return Response.status(Response.Status.CREATED).entity(created).build()
    }

    @PUT
    @Path("/{id}")
    fun updateMovie(@PathParam("id") id: Long, movieDTO: MovieDTO): MovieDTO =
        service.updateMovie(id, movieDTO)

    @DELETE
    @Path("/{id}")
    fun deleteMovie(@PathParam("id") id: Long): Response {
        service.deleteMovie(id)
        return Response.noContent().build()
    }
} 
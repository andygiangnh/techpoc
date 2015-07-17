package com.andy.rest;
 
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cassandra.Movie;
import cassandra.MoviePersistence;

@Component
@Path("/movie")
public class MovieService {
	
	@Autowired
	MoviePersistence movieDAO;
 
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMsg(@PathParam("param") String msg) {
 
	   Optional<Movie> m = movieDAO.queryMovieByTitle(msg);
	   
	   //movieDAO.close();
		
	   return m.get();
 
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Movie movie) {
		movieDAO.persistMovie(movie.getTitle(), movie.getYear(), movie.getDescription(), movie.getMovieRow(), movie.getDustinRating());
		String result = "Track saved : " + movie;
		
		return Response.status(201).entity(result).build();
 
	}
 
}
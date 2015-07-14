package com.mkyong.rest;
 
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cassandra.Movie;
import cassandra.MoviePersistence;
 
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : ";
		
		MoviePersistence moviePer = new MoviePersistence("localhost", 9042);
		   //moviePer.persistMovie("ghost", 2015, "scary move", "3.5", "4");
		   Optional<Movie> m = moviePer.queryMovieByTitle(msg);
		   if (m != null)
			   output += m.get().toString();
		   else
			   output += "Movie not found";
		   moviePer.close();
		
		return m.get();
 
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Movie movie) {
		MoviePersistence moviePer = new MoviePersistence("localhost", 9042);
		moviePer.persistMovie(movie.getTitle(), movie.getYear(), movie.getDescription(), movie.getMovieRow(), movie.getDustinRating());
		String result = "Track saved : " + movie;
		
		return Response.status(201).entity(result).build();
 
	}
 
}
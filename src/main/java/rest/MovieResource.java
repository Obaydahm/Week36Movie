package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class MovieResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    {
        if(FACADE.getMovieCount() == 0){
            FACADE.addMovie(new Movie("Bad Boys", 2001, new String[]{"Will Smith", "Martin Lawrence"}));
            FACADE.addMovie(new Movie("Chuck n' Lee Dominating the world", 2004, new String[]{"Chuck Norris", "Bruce Lee"}));
            FACADE.addMovie(new Movie("Straight Outta Compton", 2017, new String[]{
                "Ice Cube",
                "Eazy-E",
                "Suge Knight",
                "Tupac Shakur",
                "Snoop Dogg"
            }));
            FACADE.addMovie(new Movie("Deadpool", 2016, new String[]{"Ryan Reynolds", "Char izard"}));
            FACADE.addMovie(new Movie("Ed, Edd n Eddy", 2021, new String[]{"Ed Smalls", "Edd Mediums", "Eddy Biggis"}));
            FACADE.addMovie(new Movie("Scooby Do", 2001, new String[]{"Scooby Doppy", "Shaggy Flappy"}));
        }
    }        
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") Long id) {
        return GSON.toJson(FACADE.getMovie(id));
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        return GSON.toJson(FACADE.getAllMovies());
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Movie entity) {
        FACADE.addMovie(entity);
    }
    
    /*@PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Movie entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }*/
}

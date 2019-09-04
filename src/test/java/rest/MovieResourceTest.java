package rest;

import entities.Movie;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    private static final String TEST_DB = "jdbc:mysql://localhost:3307/startcode_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    Movie m = new Movie("Bad Boys", 2001, new String[]{"Will Smith", "Martin Lawrence"});

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        //NOT Required if you use the version of EMF_Creator.createEntityManagerFactory used above        
        //System.setProperty("IS_TEST", TEST_DB);
        //We are using the database on the virtual Vagrant image, so username password are the same for all dev-databases
        
        httpServer = startServer();
        
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
   
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void closeTestServer(){
        //System.in.read();
         httpServer.shutdownNow();
    }
    
    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m);
            
           
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testServerIsUp() {
        System.out.println("1. Testing is server UP");
        given().when().get("/xxx").then().statusCode(200);
    }

    @Test
    public void testCount() throws Exception {
        System.out.println("2. Count test");
        given()
        .contentType("application/json")
        .get("/xxx/count").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("count", equalTo(1));   
    }
    
    @Test
    public void testGetAll() throws Exception {
        System.out.println("2. Get all test");
        List<String> title = new ArrayList<>();
        title.add(m.getTitle());
        given()
        .contentType("application/json")
        .get("/xxx/all").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("title", equalTo(title));   
    }
    
    @Test
    public void testGetByName() throws Exception {
        System.out.println("3. Get by name test");
        List<String> title = new ArrayList<>();
        title.add(m.getTitle());
        given()
        .contentType("application/json")
        .get("/xxx/title/{name}", "Bad Boys").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("id", equalTo(m.getId().intValue())).body("title", equalTo(m.getTitle()));  
    }
    
    @Test
    public void testGetById() throws Exception {
        System.out.println("2. Get by id test");
        List<String> title = new ArrayList<>();
        title.add(m.getTitle());
        given()
        .contentType("application/json")
        .get("/xxx/{id}", m.getId()).then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("id", equalTo(m.getId().intValue())).body("title", equalTo(m.getTitle())).body("year", equalTo(m.getYear()));  
    }
}

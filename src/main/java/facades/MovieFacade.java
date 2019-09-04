package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
    }
    
    public Movie addMovie(Movie m){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return m;
        }finally{
            em.close();
        }
    }
    
    public Movie getMovie(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Movie.class, id);
        }finally{
            em.close();
        }
    }
    
    public Movie getByTitle(String title){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m WHERE m.title = :title", Movie.class);
            tq.setParameter("title", title);
            return tq.getSingleResult();
        }finally{
            em.close();
        }
    }
    
    public List<Movie> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> tq = em.createQuery("SELECT m FROM Movie m", Movie.class);
            return tq.getResultList();
        }finally{
            em.close();
        }
    }
    
    
}

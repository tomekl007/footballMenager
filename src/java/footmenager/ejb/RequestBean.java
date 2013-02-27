/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.ejb;

import entgen.CustomerGen;
import entgen.GroupsGen;
import entgen.LeagueGen;
import entgen.TeamGen;

import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tomek
 * 
 */
@Stateless
public class RequestBean {
    private static Logger logger = Logger.getLogger("footballMenager.ejb.RequestBean");
    @PersistenceContext
    private EntityManager em;
    
    public long createGroup( String name){
        try{
           GroupsGen g1 = new GroupsGen();
           g1.setName(name);
           logger.info("Created group " );
            em.persist(g1);
          
            logger.info("Persisted group "); 
           return g1.getId();
        }catch(Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    
    }
    
   public void createPersonToGroupUser(
        String firstname,
        String lastname,
        String email,
       // String address,
        String city,
        String password, 
        long groupId){
       try{
           GroupsGen g = em.find(GroupsGen.class, groupId);
          logger.info("Found group ID " + g.toString());
           
           CustomerGen c = new CustomerGen();
           c.setCity(city);
           c.setEmail(email);
           c.setLastname(lastname);
           c.setFirstname(firstname);
           c.setOwnedGroup(g);
           c.setPassword(password);
          
           
           
           g.getPersonList().add(c);
           
           logger.info("Created Customer" + c.getId()+ c.getFirstname() );
            em.persist(c);
          
            logger.info("Persisted Customer "+ c.getId() + c.getFirstname()); 
           
        }catch(Exception ex) {
            throw new EJBException(ex.getMessage());
        }
       
       
       
        
    }
   
    public void createCustomerToUsersGroup(CustomerGen cg){
        
        try{
            Query createNamedQuery = em
                                     .createNamedQuery("GroupsGen.findByName");

        createNamedQuery.setParameter("name", "Users");

       
            GroupsGen p =  (GroupsGen) createNamedQuery.getSingleResult();
            logger.info("i found groups  by name : " + p.getName() );
            
            cg.setOwnedGroup(p);
            em.persist(cg);
          
            logger.info("Persisted cutomer,"+ cg.getFirstname()+ "and added to group + "); 
           
        }catch(Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    
    
    }
   
   
       public long createLeague(String name, String country){
       try{
           
           
          LeagueGen l = new LeagueGen();
          l.setCountry(country);
          l.setName(name);
           
          
        
           
           logger.info("Created League" + l.getName()+ l.geCountry()  );
            em.persist(l);
          
            logger.info("Persisted League" + l.getName()+ l.geCountry() + l.getId()); 
           return l.getId();
        }catch(Exception ex) {
            throw new EJBException(ex.getMessage());
        }
       }
       
       //TO DO create team in specyfic league, add team to league, set team.setOwnedLeague
   
       public long createTeamInSpecyficLeague(long leagueId, String name, Integer budget){
           try{
           LeagueGen l = em.find(LeagueGen.class, leagueId);
           logger.info("Found league ID " + l.toString());
           
           TeamGen t = new TeamGen();
           t.setName(name);
           t.setBudget(budget);
           t.setLeagueBelong(l);
           l.getTeams().add(t);
           
            logger.info("Created Team "+ t.getName() + "in league : "+ l.getName());
            em.persist(t);
         
            logger.info("Persisted Team "+ t.getName() + "in league : "+ l.getName()); 
             return t.getId();
           }catch(Exception ex) {
            throw new EJBException(ex.getMessage());
        }
           
       }
       
       public TeamGen findTeamById(long id){
           
           try{
           TeamGen t = em.find(TeamGen.class, id);
          logger.info("Found TeamGen ID " + t.toString());
          return t;
           }catch(Exception ex) {
            throw new EJBException(ex.getMessage());
        }
           
       }
       
     
    
}

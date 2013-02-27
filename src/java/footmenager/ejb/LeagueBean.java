/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.ejb;

import entgen.LeagueGen;
import entgen.PersonGen;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomek
 */
@Stateless
public class LeagueBean extends AbstractFacade<LeagueGen>{
   // private static Logger logger = Logger.getLogger("footballMenager.ejb.LeagueBean" );
    @PersistenceContext
    private EntityManager em;

    public LeagueBean() {
        super(LeagueGen.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*  public List<LeagueGen> getAllLeagues(){
         List<LeagueGen> p = (List<LeagueGen>) em.createNamedQuery("LeagueGen.findAll")
                                          .getResultList();
        logger.info(p.isEmpty()? "leagues is empty":p.toString());
        return p;
    }*/
}

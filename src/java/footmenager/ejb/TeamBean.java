/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.ejb;


import entgen.TeamGen;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomek
 */
@Stateless
public class TeamBean extends AbstractFacade<TeamGen>{
    @PersistenceContext
    private EntityManager em;

    public TeamBean() {
        super(TeamGen.class);
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

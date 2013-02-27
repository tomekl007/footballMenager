/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.web;

import entgen.LeagueGen;
import footmenager.ejb.LeagueBean;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author Tomek
 */


@Named(value = "leagueController")
@SessionScoped
public class LeagueController implements Serializable {
    
    @EJB
    LeagueBean ejbFacade;
   

    public List<LeagueGen> getAllLeagues() {
        
        
        return ejbFacade.findAll();
        
    }
    
   // public List<LeagueGen> getAllLeaguesByQuery(){
 //     return  ejbFacade.getAllLeagues();
 //   
 //   }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.web;

import entgen.LeagueGen;
import entgen.TeamGen;
import footmenager.ejb.RequestBean;
import footmenager.ejb.TeamBean;
import footmenager.util.PageNavigation;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Tomek
 */
@Named(value = "teamController")
@SessionScoped
public class TeamController implements Serializable {
     private static final Logger logger = Logger.getLogger(
                CustomerController.class.getCanonicalName());
    
    String currentName;
    Integer currentBudget;
    long leagueId;
    TeamGen current;
    
    LeagueGen league;
    
    @EJB 
    RequestBean rb;
    
    // used for wizard
    private int step = 1;
    
    
     public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String cn) {
        this.currentName = cn;
    }
    
      public Integer getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(Integer cb) {
        this.currentBudget = cb;
    }
    
      public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long l) {
        this.leagueId = l;
    }
    
    public void setLeague(LeagueGen l){
        this.league = l;
    }
    public LeagueGen getLeague(){
        return league;
    }
    
    
    
     public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
    
     public TeamGen getSelected() {
        if (current == null) {
           current = new TeamGen();
           
        }

       return current;
    }
    
    
     public PageNavigation nextStep() {
        setStep(getStep() + 1);

        return PageNavigation.CREATE;
    }
     
     public PageNavigation create() {
        try {
            logger.info("attemp to create team with league id: " + this.leagueId +" currenName: " + currentName);
            //zmienic 5 na leagueId
            long id = rb.createTeamInSpecyficLeague(5, currentName, currentBudget);
            current = rb.findTeamById(id);
 
            setStep(2);

            return PageNavigation.CREATE;
        } catch (Exception e) {
            
              return null;
        }
    }
     
     public PageNavigation done() {
       setStep(1);
       this.currentBudget = null;
       this.currentName = null;
       this.league = null;
       current = null;

        return PageNavigation.INDEX;
    }
     
  //   private TeamBean getFacade() {
   //     return ejbFacade;
  //  }
    
}

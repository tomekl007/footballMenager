/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.ejb;

import entgen.AdministratorGen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Tomek
 */
@Singleton
@Startup
public class ConfigBean {
      private static Logger logger = Logger.getLogger("footballMenager.ejb.ConfigBean" );
    @EJB
    private RequestBean rb;
 //  @EJB
//   private GroupsBean groupsBean;
  //  @EJB
   // private UserBean userBean;
    @EJB
   private AdministratorBean adminBean;
            
    @PostConstruct
    public void createData() {
    
        rb.createGroup("Administrator");
       long g1 = rb.createGroup("Users");
       
       //od tego momentu sa bledy
     
       rb.createPersonToGroupUser("Tomasz","Lelek","tolek69@interia.pl","zelczyna","180192",g1);
     
      
       //  c1.setPassword("180192");
      // c1.setOwnedGroup(g1);
     //  userBean.createUser(c1);
     //  g1.getPersonList().add(c1);
     //  groupsBean.edit(g1);
       //create admin
       AdministratorGen a = new AdministratorGen();
       a.setFirstname("admin");
       a.setLastname("admin");
       
       a.setCity("krk");
       a.setEmail("admin@example.com");
       a.setPassword("1111");
       adminBean.create(a);
       
       logger.info("after all in Singelton group users consist:  " // g1.getPersonList() 
                    + "user admin belongs to group : " + a.getOwnedGroup());
                         
      
        
   //  groupsBean.createGroup(1, "Users");
        
       //league team-------
      
      long espId = rb.createLeague("Primera Division","Spain" );
      rb.createTeamInSpecyficLeague(espId,"Real Madrid", 100000000);
        
        
    }
}

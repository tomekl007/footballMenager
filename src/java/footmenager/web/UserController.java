/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package footmenager.web;


import entgen.PersonGen;
import footmenager.ejb.UserBean;

import footmenager.qualifiers.LoggedIn;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author markito
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {
     private static Logger logger = Logger.getLogger("footballMenager.web.UserController" );
    private static final String BUNDLE = "/Bundle";
    @Inject
    CustomerController customerController;
    PersonGen user;
    @EJB
    private UserBean ejbFacade;
    private String password;
    private String username;

    /**
     * Creates a new instance of Login
     */
    public UserController() {
    }

    /**
     * Login method based on
     * <code>HttpServletRequest</code> and security realm
     */
    public String login() {
      //  FacesContext context = FacesContext.getCurrentInstance();
      //  HttpServletRequest request = (HttpServletRequest) context.getExternalContext()
     //                                                            .getRequest();
        String result;
        logger.info("user try to login : "+ this.getUsername() + this.getPassword());
      //  try {
         //   request.login(
         //           this.getUsername(),
         //           this.getPassword());
            if(loginTemp(this.getUsername(), this.getPassword()) ){
          

            this.user = ejbFacade.getUserByEmail(getUsername());
            this.getAuthenticatedUser();

            if (isAdmin()) {
                result = "/admin/index";
            } else {
                result = "/index";
            }
     //   } catch (ServletException ex) {
      //      Logger.getLogger(UserController.class.getName())
      //            .log(Level.SEVERE, null, ex);
      //   
//
       //     result = "login";
      //  }

          return result;}else{
                
                return "login";
            }
    }
    
      public boolean loginTemp(String username, String password) {
        
     for( Iterator it =  ejbFacade.getAllUsers().iterator(); it.hasNext();){
         PersonGen user = (PersonGen) it.next();
         if(username.equals(user.getEmail()) ) {
             logger.info("first if : user.email = " + user.getEmail());
             if(password.equals(user.getPassword())){
                 logger.info("i found user " +  user.getEmail() + " with pass : " + user.getPassword()  );
               //  String msg = "You Have succesfully logged in ";
              //   FacesMessage facesMsg = new FacesMessage(msg, msg);
                // FacesContext.getCurrentInstance()
                  //      .addMessage(null, facesMsg);
               //  this.loginUser = user;
              //   rm.setCurrentUser(user);
                 
                 return true;
         
     
     } 
       
    }
   
    
  } 
     return false;
 }   

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext()
                                                                 .getRequest();

        try {
            this.user = null;

            request.logout();

            // clear the session
            ((HttpSession) context.getExternalContext()
                                  .getSession(false)).invalidate();

       
        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName())
                  .log(Level.SEVERE, null, ex);
            
        } finally {
            return "/index";
        }
    }

    /**
     * @return the ejbFacade
     */
    public UserBean getEjbFacade() {
        return ejbFacade;
    }

    @Produces
    @LoggedIn
    public PersonGen getAuthenticatedUser() {
        logger.info( user != null? user.getEmail(): "authenticated user = null");
        return user;
    }

    public boolean isLogged() {
        return (getUser() == null) ? false : true;
    }

    public boolean isAdmin() {
        if(user.getOwnedGroup().getName() == null) 
            return false ;
          if  (user.getOwnedGroup().getName().equals("Administrator")) {
                return true;
            }
        

        return false;
    }

    public String goAdmin() {
        if (isAdmin()) {
            return "/admin/index";
        } else {
            return "index";
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public PersonGen getUser() {
        return user;
    }
}

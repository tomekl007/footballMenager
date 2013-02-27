/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package footmenager.ejb;


import entgen.CustomerGen;
import entgen.PersonGen;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author markito
 */
@Stateless
public class UserBean extends AbstractFacade<CustomerGen> {
    private static Logger logger = Logger.getLogger("footballMenager.ejb.UserBean" );
    @PersistenceContext
    private EntityManager em;

    public UserBean() {
        super(CustomerGen.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Create a new user verifying if the user already exists
     * TODO: Create custom exceptions ?
     * @param customer
     * @return
     */
    public boolean createUser(CustomerGen customer) {
        // check if user exists
        if (getUserByEmail(customer.getEmail()) == null) {
            super.create(customer);

            return true;
        } else {
            return false;
        }
    }

    public PersonGen getUserByEmail(String email) {
        Query createNamedQuery = getEntityManager()
                                     .createNamedQuery("PersonGen.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList()
                                .size() > 0) {
            PersonGen p =  (PersonGen) createNamedQuery.getSingleResult();
            logger.info("i found person by email : " + p.getFirstname()+ p.getEmail());
            return p;
        } else {
            return null;
        }
    }
    
    
    public List<PersonGen> getAllUsers(){
         List<PersonGen> p = (List<PersonGen>) em.createNamedQuery("PersonGen.findAll")
                                          .getResultList();
        logger.info(p.isEmpty()? "persons is empty":p.toString());
        return p;
    }
}

/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package footmenager.ejb;


import entgen.AdministratorGen;
import entgen.GroupsGen;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author ievans
 */
@Stateless
public class AdministratorBean extends AbstractFacade<AdministratorGen> {
    private static Logger logger = Logger.getLogger("footballMenager.ejb.AdministratorBean");
    @PersistenceContext
    private EntityManager em;

    public AdministratorBean() {
        super(AdministratorGen.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(AdministratorGen admin) {
        GroupsGen adminGroup = (GroupsGen) em.createNamedQuery("GroupsGen.findByName")
                                       .setParameter("name", "Administrator")
                                       .getSingleResult();
        logger.info("found group with name = administrator " + adminGroup.getName());
        admin.setOwnedGroup(adminGroup);
             
        adminGroup.getPersonList()
                  .add(admin);
        em.persist(admin);
        logger.info("so Administrator Persist : " +admin.getFirstname()+ "  " +admin.getId()  );
        em.merge(adminGroup);
    }

    @Override
    public void remove(AdministratorGen admin) {
        GroupsGen adminGroup = (GroupsGen) em.createNamedQuery("GroupsGen.findByName")
                                       .setParameter("name", "Administrator")
                                       .getSingleResult();
        adminGroup.getPersonList()
                  .remove(admin);
        em.remove(em.merge(admin));
        em.merge(adminGroup);
    }
}

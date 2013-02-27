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


import entgen.GroupsGen;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author ievans
 */
@Stateless
public class GroupsBean extends AbstractFacade<GroupsGen> {
    @PersistenceContext
    private EntityManager em;

    public GroupsBean() {
        super(GroupsGen.class);
    }
    
    public boolean createGroup(GroupsGen group) {
        // check if user exists
       
            super.create(group);
            return true;
    }
    
    public boolean createGroup(Long Id, String name){
        GroupsGen g = new GroupsGen(Id,name);
        super.create(g);
        return true;
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}

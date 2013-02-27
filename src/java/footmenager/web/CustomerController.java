/*
 * Copyright 2012 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package footmenager.web;


import entgen.CustomerGen;
import entgen.PersonGen;
import footmenager.ejb.RequestBean;
import footmenager.ejb.UserBean;

import footmenager.qualifiers.LoggedIn;
import footmenager.util.PageNavigation;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {
    private static final String BUNDLE = "/Bundle";
    private static final Logger logger = Logger.getLogger(
                CustomerController.class.getCanonicalName());
    @Inject
    @LoggedIn
    PersonGen authenticated;
    @EJB
    private UserBean ejbFacade;
    
    private CustomerGen current;
    
    private int selectedItemIndex;
    
    @EJB RequestBean rb;

    public CustomerController() {
    }

    public CustomerGen getSelected() {
        if (current == null) {
            current = new CustomerGen();
            selectedItemIndex = -1;
        }

        return current;
    }

    private UserBean getFacade() {
        return ejbFacade;
    }

  //  public PageNavigation prepareList() {
    //    recreateModel();

      //  return PageNavigation.LIST;
    //}
  
  //  private void recreateModel() {
  //      teams = null;
  //  }
    

    public PageNavigation prepareCreate() {
        current = new CustomerGen();
        selectedItemIndex = -1;

        return PageNavigation.CREATE;
    }

    private boolean isUserDuplicated(PersonGen p) {
        return (getFacade()
                    .getUserByEmail(p.getEmail()) == null) ? false : true;
    }

    public PageNavigation create() {
        try {
            if (!isUserDuplicated(current)) {
                // password encrypt
                current.setPassword(current.getPassword());
                
               // getFacade()
               //     .createUser(current);
                rb.createCustomerToUsersGroup(current);
              
            } else {
               
            }

            //return prepareCreate();
            return PageNavigation.INDEX;
        } catch (Exception e) {
           

            return null;
        }
    }

    

    public PageNavigation update() {
        try {
            logger.info("Updating customer ID:" + current.getId());
            getFacade()
                .edit(current);
           

            return PageNavigation.VIEW;
        } catch (Exception e) {
          

            return null;
        }
    }

   

    

    private void performDestroy() {
        try {
            getFacade()
                .remove(current);
          
        } catch (Exception e) {
            
        }
    }

    

   
  public void setCustomer(CustomerGen user) {
        this.authenticated = user;
    }

    public PersonGen getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(PersonGen p) {
        this.authenticated = p;
    }

   /* @FacesConverter(forClass = Customer.class)
    public static class CustomerControllerConverter implements Converter {
        public Object getAsObject(
            FacesContext facesContext,
            UIComponent component,
            String value) {
            if ((value == null) || (value.length() == 0)) {
                return null;
            }

            CustomerController controller = (CustomerController) facesContext.getApplication()
                                                                             .getELResolver()
                                                                             .getValue(
                        facesContext.getELContext(),
                        null,
                        "customerController");

            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);

            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);

            return sb.toString();
        }

        public String getAsString(
            FacesContext facesContext,
            UIComponent component,
            Object object) {
            if (object == null) {
                return null;
            }

            if (object instanceof Customer) {
                Customer o = (Customer) object;

                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException(
                        "object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: "
                        + CustomerController.class.getName());
            }
        }
    }*/
}

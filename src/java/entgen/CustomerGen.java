/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entgen;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Tomek
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CustomerGen.findAll",query = "SELECT c FROM CustomerGen c")
    , @NamedQuery(name = "CustomerGen.findById", query = "SELECT c FROM CustomerGen c WHERE c.id = :id")
    , @NamedQuery(name = "CustomerGen.findByFirstname", query = "SELECT c FROM CustomerGen c WHERE c.firstname = :firstname")
    , @NamedQuery(name = "CustomerGen.findByLastname", query = "SELECT c FROM CustomerGen c WHERE c.lastname = :lastname")
    , @NamedQuery(name = "CustomerGen.findByEmail", query = "SELECT c FROM CustomerGen c WHERE c.email = :email")
  //  , @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address")
    , @NamedQuery(name = "CustomerGen.findByCity", query = "SELECT c FROM CustomerGen c WHERE c.city = :city")
})
public class CustomerGen extends PersonGen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  //  @OneToOne(mappedBy = "coach")
 //   private TeamGen ownedTeam;

    
    public CustomerGen() {
      //  this.customerOrderList = new ArrayList<CustomerOrder>();
        
    }
    
  //  public void setOwnedTeam(TeamGen t){
 //       this.ownedTeam = t;
 //   }
    
 //   public TeamGen getOwnedTeam(){
 //       return ownedTeam;
 //   }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerGen)) {
            return false;
        }
        CustomerGen other = (CustomerGen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entgen.CustomerGen[ id=" + id + " ]";
    }
    
}

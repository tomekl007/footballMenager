/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entgen;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Tomek
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "PersonGen.findAll",query = "SELECT p FROM PersonGen p")
    , @NamedQuery(name = "PersonGen.findById", query = "SELECT p FROM PersonGen p WHERE p.id = :id")
    , @NamedQuery(name = "PersonGen.findByFirstname", query = "SELECT p FROM PersonGen p WHERE p.firstname = :firstname")
    , @NamedQuery(name = "PersonGen.findByLastname", query = "SELECT p FROM PersonGen p WHERE p.lastname = :lastname")
    , @NamedQuery(name = "PersonGen.findByEmail", query = "SELECT p FROM PersonGen p WHERE p.email = :email")
   // , @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address")
    , @NamedQuery(name = "PersonGen.findByCity", query = "SELECT p FROM PersonGen p WHERE p.city = :city")
})
public class PersonGen implements Serializable {
    @ManyToOne
    private GroupsGen ownedGroup;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private TeamGen ownedTeam;
    
 // @Size(min = 3, max = 45, message = "{person.city}")
  protected String city;
 /// @Size(min = 3, max = 45, message = "{person.email}")
  protected String email;
 // @Size(min = 3, max = 50, message = "{person.firstname}")
   protected String firstname;
 //    @Size(min = 3, max = 100, message = "{person.lastname}")
   protected String lastname;
 //    @Size(min = 7, max = 100, message = "{person.password}")
  protected String password;
  
   public PersonGen() {
        
    }

 //   public Person(Integer id) {
  //      this.id = id;
 ///     
  //  }

    public PersonGen(
        String firstName,
        String lastName,
        String email,
       String city,
        GroupsGen group) {
       this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.city = city;
        this.ownedGroup = group;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setOwnedTeam(TeamGen t){
        this.ownedTeam = t;
    }
    
    public TeamGen getOwnedTeam(){
        return ownedTeam;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
  
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }

    public GroupsGen getOwnedGroup() {
        return ownedGroup;
    }

    public void setOwnedGroup(GroupsGen group) {
        this.ownedGroup = group;
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
        if (!(object instanceof PersonGen)) {
            return false;
        }
        PersonGen other = (PersonGen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entgen.PersonGen[ id=" + id + " ]";
    }
    
}
